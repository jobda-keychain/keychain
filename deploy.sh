#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/app/deploy
cd $REPOSITORY

APP_NAME=keychain
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z "$CURRENT_PID" ]
then
  echo "> 종료할 것 없음." >> /home/ubuntu/app/deploy.log
else
  echo "> sudo kill -15 $CURRENT_PID" >> /home/ubuntu/app/deploy.log
  kill -15 "$CURRENT_PID" >> /home/ubuntu/app/deploy.log 2>&1
  sleep 5
fi

echo "> $JAR_PATH 배포"
nohup java -jar -Dspring.config.location=classpath:/,file:///home/ubuntu/app/application-prod.properties "$JAR_PATH" >> /home/ubuntu/app/deploy.log 2>&1 &

echo "[$(date)] server deploy" >> /home/ubuntu/app/deploy.log
