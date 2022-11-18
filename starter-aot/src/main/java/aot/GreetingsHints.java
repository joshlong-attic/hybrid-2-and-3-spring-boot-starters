package aot;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;

class GreetingsHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        System.out.println("registering hints for k8s.Greeting");
      hints.reflection()
              .registerType( TypeReference.of("k8s.Greeting") , MemberCategory.values()) ;
    }
}
