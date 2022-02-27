CREATE TABLE monitoring
(
    id         INT          NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    status     VARCHAR(5)   NOT NULL,
    url        VARCHAR(255) NOT NULL,

    CONSTRAINT PK_MONITORING PRIMARY KEY (id)
);