package com.lxs.da.client.console;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version: version
 * @author: lixinsong
 * @className: BaseClient
 * @packageName: com.lxs.da.client.console
 * @description:
 * @data: 2020/6/25
 **/

@Builder
@Data
public class BaseClient {

    private String accessAddress;

    private String accessKey;

    private String secretKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);

    @Tolerate
    BaseClient() {
    }

    protected static <T> T handlePair(Pair<Integer, String> pair, Class<T> clazz, int defaultStatus)
            throws Exception {
        if (pair == null) {
            LOGGER.error("Can't connect to bdl service");
            throw new Exception();
        }
        if (pair.getKey() != defaultStatus) {
            LOGGER.debug("Error response status: {}, message: {}", pair.getKey(), pair.getValue());
            ErrorResponse errorResponse = JsonUtil.toObject(pair.getValue(), ErrorResponse.class);
            if (errorResponse == null) {
                throw new UnexpectedResponseException();
            } else {
                throw new BdlServiceClientException(errorResponse.getMessage(), errorResponse.getCode(),
                        pair.getKey());
            }
        }

        if (clazz == null) {
            return null;
        }
        T response = JsonUtil.toObject(pair.getValue(), clazz);
        if (response == null) {
            LOGGER.error("parse json with error:" + pair.getValue());
            throw new RuntimeException("parse json error");
        }
        return response;
    }

}
