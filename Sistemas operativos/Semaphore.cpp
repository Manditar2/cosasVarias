#include "Semaphore.hpp"
#include <iostream>

Semaphore::Semaphore(int size) : count_(size) {}
void Semaphore::signal(){
    count_++;
}
void Semaphore::wait(){
    while(!try_wait()){}
}
bool Semaphore::try_wait(){
    int count = count_;
    if(count) {
        return count_.compare_exchange_strong(count, count - 1);
    } else {
        return false;
    }
}
