#!/bin/sh

jar cmf MANIFEST.MF RECT.jar -C  ../classes/ .
jar uf RECT.jar -C  ../res/ .