#!/bin/sh
PIDFile="xfxd.pid"

function check_if_pid_file_exists {
    if [ ! -f $PIDFile ]
    then
 echo "PID file not found: $PIDFile"
        exit 1
    fi
}

function check_if_process_is_running {
 if [ ! -f $PIDFile ]; then
   return 1
 fi
 ps -p $(print_process) | grep 'java'
 return $?
}

function print_process {
    echo $(<"$PIDFile")
}

case "$1" in
  status)
    if check_if_process_is_running
    then
      echo $(print_process)" is running"
    else
      echo "Process not running"
    fi
    ;;
  stop)
    if ! check_if_process_is_running
    then
      echo "Process  already stopped"
      exit 0
    fi
    kill -TERM $(print_process)
    echo -ne "Waiting for process to stop"
    NOT_KILLED=1
    for i in {1..20}; do
      if check_if_process_is_running
      then
        echo -ne "."
        sleep 1
      else
        NOT_KILLED=0
      fi
    done
    echo
    if [ $NOT_KILLED = 1 ]
    then
      echo "Cannot kill process "
      exit 1
    fi
    echo "Process stopped"
    ;;
  start)
    if [ -f $PIDFile ] && check_if_process_is_running
    then
      echo "Process $(print_process) already running"
      exit 1
    fi
    (java -jar *.jar &) > /dev/null
    echo "Process started"
    ;;
  restart)
    $0 stop
    if [ $? = 1 ]
    then
      exit 1
    fi
    $0 start
    ;;
  debug)
    if [ -f $PIDFile ] && check_if_process_is_running
    then
      echo "Process $(print_process) already running"
      exit 1
    fi
    (java -server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8899 -jar *.jar &)  > /dev/null
    echo "Process debug started"
    ;;
  *)
    echo "Usage: $0 {start|stop|restart|status|debug}"
    exit 1
esac

exit 0