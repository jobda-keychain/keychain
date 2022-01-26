#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/app

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> 현재 구동 중인 app pid : "

CURRENT_PID=$ pgrep -fl ${JAR_NAME##*/} | grep java | awk '{print $1}'

echo "현재 구동 중인 app pid : $CURRENT_PID"

if [ ${CURRENT_PID} ]; then
  echo "현재 구동 중인 app이 없으므로 종료하지 않습니다."
else
  echo "> kill -9 $CURRENT_PID"
  kill -9 $CURRENT_PID
  sleep 5
fi

echo "> 새 app 배포"

echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar $JAR_NAME &
