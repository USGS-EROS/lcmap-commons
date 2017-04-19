(ns lcmap.commons.chip)

(defn difference
  "Calculate difference between a point and 'prior' point on an interval.

  The value of this function can be used to answer the question,
  what do I subtract from a point to find the point of the nearest
  chip that contains it?

  Geospatial raster data geometry can be somewhat counter-inuitive
  because coordinates and pixel geometry are expressed with both
  positive and negative values.

  Along the x-axis, pixel size (and thus the interval) is a positive
  number (e.g. 30 * 100). Along the y-axis though, the pixel size and
  interval is a _negative_ value. Even though this may seem peculiar,
  using a negative value helps us avoid special cases for finding the
  nearest chip-point.

  Parameters:
  * point    - a scalar value on the real number line
  * interval - a scalar value describing regularly spaced points
               on the real number line"
  [point interval]
  (mod point interval))

(defn near
  "Find nearest point given an interval and offset.

  The nearest point will be lesser than the point for a positive
  interval, and greater than the point for a negative interval as
  is required when finding an 'upper-left' point on a cartesian
  plane.

  This function is used to calculate the nearest points along the
  x- and y- axis.

  Parameters:
  * point    - a scalar value on the real number line
  * interval - a scalar value describing regularly spaced points
               on the real number line
  * offset   - a scalar value used to shift point before and after
               finding the 'preceding' interval."
  [point interval offset]
  (-> point
      (- offset)
      (/ interval)
      (Math/floor)
      (* interval)
      (+ offset)))

(defn point->chip
  "Find the nearest containing chip's point.

  The resulting `x` value will be less than or equal to the input
  while the resulting `y` value will be greater than or equal.

  For this function to work properly, intervals and offsets must be
  expressed in terms of projection coordinate system 'easting' and
  'northing' units.

  Along the x-axis, this works as expected. The interval is a multiple
  of pixel size and chip size (e.g. 30 * 100 = 3000). Along the y-axis
  the interval is negative because the pixel size is negative, as you
  move from the origin of a raster file, the y-axis value _decreases_.

  The offset value is used for grids that are not aligned with the
  origin of the projection coordinate system.

  * point     - a map containing :x and :y coordinates
  * chip-spec - a map containing the interval and offset for x-
                and y-axis"
  [{x :x y :y :as point}
   {:keys [x-interval y-interval x-offset y-offset] :as chip-spec}]
  {:x (near x x-interval x-offset)
   :y (near y y-interval y-offset)})

(defn snap
  "Transform an arbitrary projection system coordinate (x,y) into the
   coordinate of the chip that contains it.

  This function only works when working with points on a cartesian plane,
  it cannot be used with other coordinate systems.

  * x         - x coordinate
  * y         - y coordinate
  * chip-spec - parameters for a chip's grid system"
  [x y chip-spec]
  (let [{:keys [chip_x chip_y shift_x shift_y]} chip-spec
        chip (point->chip {:x x :y y}
                          {:x-interval chip_x
                           :y-interval chip_y
                           :x-offset shift_x
                           :y-offset shift_y})]

    [(long (:x chip))
     (long (:y chip))]))
