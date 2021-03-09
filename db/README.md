# Setting up the Postgres DB and PgAdmin

Requirements:
- Docker desktop

Instructions:
- cd into the "db" directory
- run: docker-compose up
- go to localhost:80 in a web browser
- log in using the given credentials (default email: user@domain.com password: SuperSecret)
- right click on Servers
- Create > Server...
- Under the Connection tab add the ip address which results from the following command:
    - docker inspect -f "{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}" <container_id_of_the_db>
    - Hint: you can find the container ID using: docker ps
- Port: 5432
- Maintenance database: postgres
- Username: postgres
- Password: p0stgres
- Click on 'Save'

If you want to delete the DB and the PGAdmin containers: docker-compose down