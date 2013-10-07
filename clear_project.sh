#! /bin/bash

echo "Remove all boring files in project starts."

cd presentation
sudo chmod +x clear_presentation.sh
./clear_presentation.sh
cd ..

cd rapport
sudo chmod +x clear_rapport.sh
./clear_rapport.sh
cd ..

cd trunk/jimmy
sudo chmod +x clear_bin.sh
./clear_bin.sh
cd ..
cd ..

echo "Remove all boring files in project end."
