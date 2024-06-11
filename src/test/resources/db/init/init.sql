USE securities_info

CREATE TABLE BATCH_JOB_INSTANCE  (
    JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
    VERSION BIGINT ,
    JOB_NAME VARCHAR(100) NOT NULL,
    JOB_KEY VARCHAR(32) NOT NULL,
constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
    JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
    VERSION BIGINT  ,
    JOB_INSTANCE_ID BIGINT NOT NULL,
    CREATE_TIME DATETIME(6) NOT NULL,
    START_TIME DATETIME(6) DEFAULT NULL ,
    END_TIME DATETIME(6) DEFAULT NULL ,
    STATUS VARCHAR(10) ,
    EXIT_CODE VARCHAR(2500) ,
    EXIT_MESSAGE VARCHAR(2500) ,
    LAST_UPDATED DATETIME(6),
    constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
    references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
    JOB_EXECUTION_ID BIGINT NOT NULL ,
    PARAMETER_NAME VARCHAR(100) NOT NULL ,
    PARAMETER_TYPE VARCHAR(100) NOT NULL ,
    PARAMETER_VALUE VARCHAR(2500) ,
    IDENTIFYING CHAR(1) NOT NULL ,
    constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
    STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
    VERSION BIGINT NOT NULL,
    STEP_NAME VARCHAR(100) NOT NULL,
    JOB_EXECUTION_ID BIGINT NOT NULL,
    CREATE_TIME DATETIME(6) NOT NULL,
    START_TIME DATETIME(6) DEFAULT NULL ,
    END_TIME DATETIME(6) DEFAULT NULL ,
    STATUS VARCHAR(10) ,
    COMMIT_COUNT BIGINT ,
    READ_COUNT BIGINT ,
    FILTER_COUNT BIGINT ,
    WRITE_COUNT BIGINT ,
    READ_SKIP_COUNT BIGINT ,
    WRITE_SKIP_COUNT BIGINT ,
    PROCESS_SKIP_COUNT BIGINT ,
    ROLLBACK_COUNT BIGINT ,
    EXIT_CODE VARCHAR(2500) ,
    EXIT_MESSAGE VARCHAR(2500) ,
    LAST_UPDATED DATETIME(6),
    constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
    references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
    STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT ,
    constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
    references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
    JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT ,
    constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
    references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);

CREATE TABLE bond_interest_type (
    code CHAR(1) PRIMARY KEY NOT NULL, -- 채권 이자 유형 코드
    name VARCHAR(100) NOT NULL -- 채권 이자 유형 코드 명칭
);

CREATE TABLE bond_interest_change (
    code CHAR(1) PRIMARY KEY NOT NULL, -- 금리 변동 구분 코드
    name VARCHAR(100) NOT NULL -- 금리 변동 구분 코드 명칭
);

CREATE TABLE bond_securities_item_kind (
    code VARCHAR(4) PRIMARY KEY NOT NULL, -- 유가증권 종목 종류 코드
    name VARCHAR(100) NOT NULL -- 유가증권 종목 종류 코드 명칭
);

CREATE TABLE bond_issuer (
    code VARCHAR(13) PRIMARY KEY NOT NULL, -- 법인등록번호,
    name VARCHAR(100) NOT NULL, -- 채권 발행인 명칭
    grade VARCHAR(5), -- 신용등급
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 레코드 생성 시간
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL -- 레코드 업데이트 시간,-- 기업 신용등급
);

CREATE TABLE bond (
    isin_code VARCHAR(12) PRIMARY KEY NOT NULL, -- 국제 채권 식별 번호 (ISIN 코드)
    isin_code_name VARCHAR(100) NOT NULL, -- 국제 채권 식별 번호 명칭
    issuer_code VARCHAR(13) NOT NULL, -- 발행인 법인등록번호
    issue_date DATE NOT NULL, -- 채권 발행 일자
    issue_format_name VARCHAR(100) NOT NULL, -- 채권 발행 형태 명칭
    surface_interest_rate DECIMAL(15,10) NOT NULL, -- 채권 표면 이자율
    expired_date DATE NOT NULL, -- 채권 만기 일자
    securities_item_kind_code VARCHAR(4) NOT NULL, -- 유가증권 종목 종류 코드
    interest_change_code CHAR(1) NOT NULL, -- 금리 변동 구분 코드
    interest_type_code CHAR(1) NOT NULL, -- 채권 이자 유형 코드
    issue_currency_code VARCHAR(3) NOT NULL, -- 채권 발행 통화 코드
    price DECIMAL(15, 2),
    priced_date DATE, -- 채권 가격 일자-- 채권 가격
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 레코드 생성 시간
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL, -- 레코드 업데이트 시간,
    FOREIGN KEY (securities_item_kind_code) REFERENCES bond_securities_item_kind(code),
    FOREIGN KEY (interest_change_code) REFERENCES bond_interest_change(code),
    FOREIGN KEY (interest_type_code) REFERENCES bond_interest_type(code),
    FOREIGN KEY (issuer_code) REFERENCES bond_issuer(code)
);

CREATE TABLE bond_grade_rank (
    grade VARCHAR(5) not null, -- 기업 신용등급,
    `rank` TINYINT not null, -- grade 별 랭킹
    isin_code VARCHAR(12) PRIMARY KEY NOT NULL, -- 법인등록번호,
    isin_code_name VARCHAR(100) NOT NULL, -- 국제 채권 식별 번호 명칭
    surface_interest_rate DECIMAL(15,10) NOT NULL, -- 채권 표면 이자율
    expired_date DATE NOT NULL, -- 채권 만기 일자
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);