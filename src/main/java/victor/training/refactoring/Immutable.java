package victor.training.refactoring;

import java.util.List;

class MutableHost { //think "Entity"
    public Immutable immutable;
}

public class Immutable {
    public int x;
    public List<Integer> numbers;
    public Other other;
}

class Other {}
