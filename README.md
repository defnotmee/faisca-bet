# FAISCA-BET
Casa de apostas hipotética para o projeto de MC322.

# Usage

Tem dois projetos maven acoplados: o FaiscaAPI e o FaiscaFront

Para rodar o aplicativo, vá na pasta FasicaFront e rode:

```
sudo apt install maven
mvn clean javafx:run
```

Para ter todas as funcionalidades, é necessário se conectar a um servidor rodando FaiscaAPI

Na hora de logar aparece um campo para preencher o ip local do servidor. Preenchê-lo te conecta a um
servidor na mesma LAN (eduroam não funciona).

Para rodar um servidor com o FaiscaAPI gere o arquivo .war com mvn package e depois colocar em um
servidor tomcat. 