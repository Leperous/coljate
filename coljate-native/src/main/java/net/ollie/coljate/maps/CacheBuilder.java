package net.ollie.coljate.maps;

import java.util.Timer;
import java.util.function.Function;

import net.ollie.coljate.utils.numeric.PositiveInteger;

import java.time.Duration;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface CacheBuilder<K, V> extends Cache<K, V> {

    static <K, V> CacheBuilder<K, V> create(final Function<? super K, ? extends V> valueFunction) {
        return MapCache.create(valueFunction);
    }

    @CheckReturnValue
    @Nonnull
    default TimedCache<K, V> withExpireAfterWrite(final Duration writeExpiration, final Timer timer) {
        return TimedCache.writeExpires(this, writeExpiration, timer);
    }

    @CheckReturnValue
    @Nonnull
    default TimedCache<K, V> withExpireAfterRead(final Duration readExpiration, final Timer timer) {
        return TimedCache.readExpires(this, readExpiration, timer);
    }

    @CheckReturnValue
    @Nonnull
    default TimedCache<K, V> withExpireAfterReadOrWrite(@Nonnull final Duration expiration, @Nonnull final Timer timer) {
        return TimedCache.readWriteExpires(this, expiration, expiration, timer);
    }

    @CheckReturnValue
    @Nonnull
    default SizeLimitedCache<K, V> withMaxSize(@Nonnull final PositiveInteger maxSize) {
        return SizeLimitedCache.create(this, maxSize);
    }

    @CheckReturnValue
    @Nonnull
    default SizeLimitedCache<K, V> withMaxSize(final int maxSize) {
        return this.withMaxSize(PositiveInteger.of(maxSize));
    }

}
