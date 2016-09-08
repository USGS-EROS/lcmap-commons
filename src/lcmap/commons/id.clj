(ns lcmap.commons.id
  "Centralizes all logic for identification in LCMAP")

(defn uuid
  "RFC 4122 v4 compliant id generator.  See https://tools.ietf.org/html/rfc4122"
  ;; If v1, v3 or v5 are desired consider using
  ;; http://danlentz.github.io/clj-uuid/ as the implementation.
  []
  (str (java.util.UUID/randomUUID)))

(defn- to-urn [type id]
  "RFC 2141 formatter. https://www.ietf.org/rfc/rfc2141.txt"
  (str "urn:" type ":" id))

(defn ucid
  "Universal campaign id generator"
  []
  (to-urn "uuid" (uuid)))

(defn ubid
  "Universal band id generator"
  []
  (to-urn "uuid" (uuid)))

;(defn input-uri
;  "RFC 3986 compliant uri generator.  See https://tools.ietf.org/html/rfc3986"
;  [] ())
