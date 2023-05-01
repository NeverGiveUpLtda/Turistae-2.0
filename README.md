# Turistae

Projeto em desenvolvimento aboradando ODS 8.9 (Promover o turismo sustentável e responsável, acessível a todos). Aplicando todos os conhecimentos adquiridos em ambiente acadêmico.

Para rodar o docker: 
                     docker system prune --volumes
                     docker build --no-cache -t turistae-app . 
                     docker run --name turistae-app -p 8080:8080 turistae-app
