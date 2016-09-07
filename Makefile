VERSION=1.0.0
PROJECT=lcmap-commons
STANDALONE=target/$(PROJECT)-$(VERSION)-SNAPSHOT-standalone.jar
ROOT_DIR = $(shell pwd)

include resources/make/code.mk
include resources/make/docs.mk
include resources/make/docker.mk
