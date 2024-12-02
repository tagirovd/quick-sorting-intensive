package stage1.graduation.strategy;

import stage1.graduation.enums.InputMethod;

import java.util.HashMap;
import java.util.Map;

public class InputData<T> {
    private final Map<InputMethod, InputStrategy> strategies = new HashMap<>();
    private InputStrategy<T> strategy;
    {
        strategies.put(InputMethod.MANUAL, new ManualInputStrategy());
        strategies.put(InputMethod.GENERATOR, new GeneratorInputStrategy());
        strategies.put(InputMethod.FILE, new FileInputStrategy());
    }

    public InputData(InputMethod method) {
        this.strategy = strategies.get(method);
    }

    public void setStrategy(InputMethod method) {
        this.strategy = strategies.get(method);
    }

    public  T[] input(Class<T> classType, int length) {
        return strategy.input(classType, length);
    }
}
