#include <cstring>
#include <ctype.h>
#include <iostream>
#include <stdlib.h>
#include <string>
#include <time.h>
#include <vector>
#include <fstream>

using namespace std;

typedef struct {
    string numeroCelular;
    string dadosSessao;
} Telefone;

string *split(string frase, string delimitador = " ")
{
    string *vetor = (string *)malloc(sizeof(string) * 3); //alocando um vetor de string com 3 posições
    int inicio = 0;
    int fim = frase.find(delimitador);
    int i = 0;
    while (fim != -1) {
        vetor[i] = frase.substr(inicio, fim - inicio);
        inicio = fim + delimitador.size();
        fim = frase.find(delimitador, inicio);
        i++;
    }
    vetor[i] = frase.substr(inicio, fim - inicio);

    return vetor;
}


int main() {
    vector<Telefone> listaSessoes;
    int opcao;

    //método que carrega a base de telefonia e armazena numero de celular e dados de sessao no vector listaSessoes
    ifstream arquivo("telephony_sessions.txt");
    if (arquivo.is_open()) {
        string linha;
        while (getline(arquivo, linha)) {
            if (!linha.empty()) {
                string *partes = split(linha, ";");
                Telefone tel;
                tel.numeroCelular = partes[0];
                tel.dadosSessao = partes[1];
                listaSessoes.push_back(tel);
                free(partes);
            }
        }
        arquivo.close();
    } else {
        cout << "Erro ao abrir o arquivo de sessoes.\n";
    }

    do {
        cout << "MENU\n";
        cout << "1 - Listar telefones\n";
        cout << "2 - Pesquisar telefone e dados de sessao\n";
        cout << "3 - Finalizar\n";
        cout << "Opcao: ";
        cin >> opcao;
        cin.ignore();

    switch (opcao) {
            case 1: 
                //metodo que lista somente os numeros de celular um abaixo do outro e no final mostra a quantidade de celulares exibidos
                cout << "\n--- Lista de Telefones ---\n";
                for (size_t i = 0; i < listaSessoes.size(); i++) {
                    cout << listaSessoes[i].numeroCelular << "\n";
                }
                cout << "Quantidade total de celulares exibidos: " << listaSessoes.size() << "\n";
                break;

            case 2:
            {
                string busca;
                cout << "Digite o numero de celular para pesquisar: ";
                getline(cin, busca);

                bool encontrado = false;
                for (size_t i = 0; i < listaSessoes.size(); i++) {
                    if (listaSessoes[i].numeroCelular == busca) {
                        cout << "Dados de sessao encontrados:\n" << listaSessoes[i].dadosSessao << "\n";
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    cout << "Numero de celular nao encontrado na base.\n";
                }
                break;
            }
            case 3:
                cout << "Obrigado por usar o sistema\n";
                break;
            default:
                cout << "Opcao invalida!\n";
                break;
        }
    } while (opcao != 3);

    return 1;
}