#!/bin/bash

# to run it we need to tell that 
# $1 is the method
# $2 is the file requested
# $3 is the data-post
# $4 is the data-get

# Used just to throw error
# mila avadika aloha ilay SRC_DIR REO
# asiana specificité ilay nom de dossier
# Script to run a php post file via the cgi and see the result

GATEWAY_INTERFACE=CGI/1.1
REQUEST_METHOD=$1
REDIRECT_STATUS=true
content=$3
CONTENT_LENGTH=${#content}
QUERY_STRING=$4
SRC_DIR="$HOME/Documents/GitHub/Web-server/src/exception/"
EX_DIR="$HOME/Documents/GitHub/Web-server/"
SCRIPT_FILENAME=$EX_DIR/$2
SCRIPT_NAME=$0

if [[ $REQUEST_METHOD == "POST" ]]; then

	CONTENT_TYPE=application/x-www-form-urlencoded; charset=UTF-8;

else

	CONTENT_TYPE=text/html; charset=UTF-8;

fi

echo $0

export REDIRECT_STATUS
export GATEWAY_INTERFACE
export REQUEST_METHOD
export SCRIPT_FILENAME
export SCRIPT_NAME
export CONTENT_LENGTH
export CONTENT_TYPE
export QUERY_STRING
export SRC_DIR
export EX_DIR

echo $content 
echo $SCRIPT_FILENAME
echo $content 
echo $content 
echo $content 
echo $content | php-cgi 2> "$SRC_DIR/phpError.html"
