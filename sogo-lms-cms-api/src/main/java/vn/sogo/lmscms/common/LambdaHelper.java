package vn.sogo.lmscms.common;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by VinhLe on 4/25/2017.
 */
public class LambdaHelper {

    public static  <T> T GetFirstOrDefault(List<T> sources, Predicate<T> predicate) {
        Optional<T> optional = sources.stream().filter(predicate).findFirst();
        return optional.isPresent() ? optional.get() : null;
    }

}
