#include <Semaphore.hpp>

Semaphore::Semaphore() : count_(0) {}
void Semaphore::signal(){
    count_++;
}
void Semaphore::wait(){
    while(!try_wait()){}
}
bool Semaphore::try_wait() {
    int count = count_;
    if(count) {
        return count_.compare_exchange_strong(count, count - 1);
    } else {
        return false;
    }
}
