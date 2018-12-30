# NoteBackup

docker run --name notebackupdb -p 5432:32770 -d postgres

docker container update --restart=always notebackupdb

docker exec -it notebackupdb psql --username postgres -c "CREATE DATABASE notebackup OWNER postgres;"

docker exec -it notebackupdb psql --username postgres -c "GRANT ALL PRIVILEGES ON DATABASE notebackup TO postgres;"