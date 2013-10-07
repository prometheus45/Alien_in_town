#! /bin/bash

echo "Remove all class bin files in trunk jimmy starts."

find . -name "*.class" -type f -print | xargs rm -f

echo "Remove all class bin files in trunk jimmy end."
