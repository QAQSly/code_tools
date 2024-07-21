@echo off
setlocal enabledelayedexpansion

rem Add all changed files
git add .
rem Commit with message
call git commit -m "update"

rem Push to remote repository
git push

echo Git operations completed.
pause