package com.aleksandar.menutest.data.repository.dataSource;

public interface LocalDataSource {

    void saveData(String key, String value);

    String getData(String key);
}
