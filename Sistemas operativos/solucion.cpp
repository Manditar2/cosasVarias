#include <iostream>
#include <vector>
#include <string>
#include <thread>
#include <mutex>
#include <queue>
#include <cstdlib>
#include <ctime>
#include <atomic>
#include "Semaphore.hpp"

Semaphore fullness(10); // Si llega a 0 entonces el buffer está lleno.
Semaphore emptyness(10); // Si llega a 0 entonces el buffer está vacio.

struct Participante {
    std::string nombre;
    std::string pais;
    int id;

    Participante(std::string nombre, std::string pais, int id)
        : nombre(nombre), pais(pais), id(id) {}

    void get_attr(Semaphore *s) {
        s->wait();
        std::cout << nombre << " " << pais << " " << id << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(5000));
        s->signal();
    }
};
std::queue<Participante> buffer;

void addParticipante(std::queue<Participante>& buffer, Participante& P, std::mutex *acceso_buffer){
    /*
    Solución al problema del producto consumidor mediante semaforos. Productor
    */

    fullness.wait(); // Si el buffer está lleno, busy_wasting.
    (*acceso_buffer).lock(); // Si no se puede acceder al buffer, se espera.
    buffer.push(P);
    (*acceso_buffer).unlock();
    emptyness.signal(); // Transmitimos la información de que se agregó un elmento.
}

void removeParticipante(std::queue<Participante>& buffer, std::mutex *acceso_buffer){
    /*
    Solución al problema productos consumidor mediante semaforos. Consumidor.
    */

    emptyness.wait(); // Si está vacio no podemos vaciar, por lo que se espera.
    (*acceso_buffer).lock();
    buffer.pop();
    (*acceso_buffer).unlock();
    fullness.signal();
}

Participante createParticipante(){
    std::string nombres[5] = {"Gabriel", "Amanda", "Jorge", "Hugo", "Alfredo"};
    std::string paises[5] = {"Peru", "Chile", "Portugal", "Alemania", "Suiza"};
    int index1 = rand() % 5;
    int index2 = rand() % 5;
    return Participante(nombres[index1], paises[index2], 1);
}



int main() {
    srand(time(0));
    Semaphore sem(1); // Primero, se define un counting semaphore de máximo 1.
    std::mutex lock;

    Participante p1 = createParticipante();
    Participante p2 = createParticipante();
    std::thread t1(&Participante::get_attr, &p1, &sem);
    std::thread t2(&Participante::get_attr, &p2, &sem);

    t1.join();
    t2.join();

    return 0;
}
