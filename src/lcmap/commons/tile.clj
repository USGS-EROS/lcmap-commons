(ns lcmap.commons.tile
  (:require [clojure.tools.logging :as log]))

(defn snap
  "Transform an arbitrary projection system coordinate (x,y) into the
   coordinate of the tile that contains it."
  [x y spec]
  (let [{:keys [tile_x tile_y shift_x shift_y]} spec
        tx (+ shift_x (- x (mod x tile_x)))
        ty (+ shift_y (- y (mod y (- tile_y))))]
    (log/debug "snap using spec (%s): (%d,%d) to (%d,%d)" spec x y tx ty)
    [(long tx) (long ty)]))
