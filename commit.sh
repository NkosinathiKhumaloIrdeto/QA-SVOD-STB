#!/bin/bash
echo "adding changes..."
git add .
echo "commiting changes"
git commit -m "Deploy - automated deploy"
echo "pushing changes"
git push
