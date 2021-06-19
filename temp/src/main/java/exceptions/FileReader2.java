package exceptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<E, F> {
    F apply(E e) throws Exception;
}

class Either<L, R> {
    private L left;
    private R right;

    private Either() {}
    public static <L, R> Either<L,R> success(R success) {
        Either<L, R> self = new Either<>();
        self.right = success;
        return self;
    }
    public static <L, R> Either<L, R> failure(L problem) {
        Either<L, R> self = new Either<>();
        self.left = problem;
        return self;
    }

    // just one example! Not complete
    public void ifSuccess(Consumer<R> op) {
        if (left == null) {
            op.accept(right);
        }
    }

    public Either<L, R> recover(Function<L, Either<L, R>> op) {
        if (left == null) return this;
        else {
            Either<L, R> res = op.apply(left);
            return res;
        }
    }

    public boolean isSuccess() {
        return left == null;
    }

    public boolean isFailure() {
        return left != null;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public static <E, F> Function<E, Either<Throwable, F>> wrap(ExFunction<E, F> op) {
        return e -> {
            try {
                return Either.success(op.apply(e));
            } catch (Throwable t) {
                return Either.failure(t);
            }
        };
    }
}

public class FileReader2 {
//    public static Either<Throwable, Stream<String>> getFromFile(String path) {
//        try {
//            return Either.success(Files.lines(Paths.get(path)));
//        } catch (Throwable t) {
//            return Either.failure(t);
//        }
//    }

    public static void main(String[] args) {
        Stream.of("a.txt", "b.txt", "c.txt")
//                .map(FileReader2::getFromFile)
                .map(Either.wrap(n -> Files.lines(Paths.get(n))))
//                .map(e -> e.recover(s -> FileReader2.getFromFile("d.txt")))
                .map(e -> e.recover(Either.wrap(s -> Files.lines(Paths.get("d.txt")))))
                .peek(e -> {
                    if (e.isFailure()) {
                        System.out.println("Ooops: " + e.getLeft().getMessage());
                    }
                })
                .filter(Either::isSuccess)
                .flatMap(Either::getRight)
                .forEach(System.out::println);
    }
}
