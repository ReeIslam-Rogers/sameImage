package util;

@FunctionalInterface
public interface ReadConfig {
    String get(String name);
}
