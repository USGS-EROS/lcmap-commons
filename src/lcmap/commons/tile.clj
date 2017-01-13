(ns lcmap.commons.tile)

(defn difference
  "Calculate difference between a point and 'prior' point on an interval.

  The value of this function can be used to answer the question,
  what do I subtract from a point to find the point of the nearest
  tile that contains it?

  Geospatial raster data geometry can be somewhat counter-inuitive
  because coordinates and pixel geometry are expressed with both
  positive and negative values.

  Along the x-axis, pixel size (and thus the interval) is a positive
  number (e.g. 30 * 100). Along the y-axis though, the pixel size and
  interval is a _negative_ value. Even though this may seem peculiar,
  using a negative value helps us avoid special cases for finding the
  nearest tile-point.

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

(defn point->tile
  "Find the nearest containing tile's point.

  The resulting `x` value will be less than or equal to the input
  while the resulting `y` value will be greater than or equal.

  For this function to work properly, intervals and offsets must be
  expressed in terms of projection coordinate system 'easting' and
  'northing' units.

  Along the x-axis, this works as expected. The interval is a multiple
  of pixel size and tile size (e.g. 30 * 100 = 3000). Along the y-axis
  the interval is negative because the pixel size is negative, as you
  move from the origin of a raster file, the y-axis value _decreases_.

  The offset value is used for grids that are not aligned with the
  origin of the projection coordinate system.

  * point     - a map containing :x and :y coordinates
  * tile-spec - a map containing the interval and offset for x-
                and y-axis"
  [{x :x y :y :as point}
   {:keys [x-interval y-interval x-offset y-offset] :as tile-spec}]
  {:x (near x x-interval x-offset)
   :y (near y y-interval y-offset)})

(defn snap
  "Transform an arbitrary projection system coordinate (x,y) into the
   coordinate of the tile that contains it.

  * x         - x coordinate
  * y         - y coordinate
  * tile-spec - parameters for a tile's grid system"
  [x y tile-spec]
  (let [{:keys [tile_x tile_y shift_x shift_y]} tile-spec
        tile (point->tile {:x x :y y}
                          {:x-interval tile_x
                           :y-interval tile_y
                           :x-offset shift_x
                           :y-offset shift_y})]

    [(long (:x tile))
     (long (:y tile))]))
