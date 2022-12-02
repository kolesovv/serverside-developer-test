CREATE TABLE IF NOT EXISTS sk_example_table
(
    id SERIAL NOT NULL,
    obj JSONB NOT NULL,

    PRIMARY KEY (id)
);

INSERT INTO sk_example_table (obj)
VALUES ('{"current":0}')
