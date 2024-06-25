# FAISCA-BET
Casa de apostas hipotética para o projeto de MC322.

# Usage

Tem dois projetos maven acoplados: o FaiscaAPI e o FaiscaFront

Para rodar o aplicativo, vá na pasta FasicaFront e use mvn clean javafx:run

Para ter todas as funcionalidades, é necessário setar o serverIp na classe App do
FaiscaFront para um Ip local rodando o FaiscaAPI

Para rodar o FaiscaAPI gere o arquivo .war com mvn package e depois colocar em um
servidor tomcat. 