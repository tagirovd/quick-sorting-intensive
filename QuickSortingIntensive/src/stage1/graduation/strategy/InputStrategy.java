package stage1.graduation.strategy;

public interface InputStrategy<T> {
    T[] input(Class<T> classType, int arrayLength);
}
