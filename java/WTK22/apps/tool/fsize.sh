#!/bin/bash
ls -l $1 | awk -F\  '{print $5}'
