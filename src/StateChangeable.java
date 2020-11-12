interface StateChangeable<T extends Enum> {
    void changeState(T status);
}