package tech.pdai.springboot.redis.utiles.lock.entity.enums;

public enum EnvParamEnums {

    PUB_EXEC_PATH_LOCK("pub.exec.pathLock"),
    SCRIPT_KEY_VALUE_REPLACE("script.key-value.replace");




    private final String value;

    EnvParamEnums(String value) {
        this.value = value;
    }
}
