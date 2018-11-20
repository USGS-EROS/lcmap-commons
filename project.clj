  (defproject gov.usgs.eros/lcmap-commons "1.0.1"
   :url "https://github.com/USGS-EROS/lcmap-commons"
    :dependencies [[clj-time/clj-time "0.15.0"]
                   [org.clojure/clojure "1.9.0"]]
                 
   :repl-options {:init-ns lcmap.commons.dev}
   :codox {
           :project {:name "lcmap.commons"
                     :description "Base common library for lcmap"}
           :namespaces [#"^lcmap.commons\."]
           :output-path "docs/master/current"
           :doc-paths ["docs/source"]
           :metadata {:doc/format :markdown
                      :doc "Documentation forthcoming"}}
   :repositories [
    ["maven" {
      :url "https://oss.sonatype.org/content/repositories/snapshots/"
      :creds :gpg}]]
   :deploy-repositories [
    ["releases" {
      :url "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
      :creds :gpg}
     "snapshots" {
      :url "https://oss.sonatype.org/content/repositories/snapshots/"
      :creds :gpg}]]
   :profiles {
              :uberjar {:aot :all}
              ;; configuration for dev environment -- if you need to make local changes,
              ;; copy `:env { ... }` into `{:user ...}` in your ~/.lein/profiles.clj and
              ;; then override values there
              :dev {
                    :dependencies [[org.clojure/tools.namespace "0.3.0-alpha3"]]
                    :aliases {}
                    :source-paths ["dev-resources/src"]}})
