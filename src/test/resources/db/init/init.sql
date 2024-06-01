USE securities_info

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
    name VARCHAR(100) PRIMARY KEY NOT NULL, -- 채권 발행인 명칭
    crno VARCHAR(13) NOT NULL -- 법인등록번호
);

CREATE TABLE bond (
    isin_code VARCHAR(12) PRIMARY KEY NOT NULL, -- 국제 채권 식별 번호 (ISIN 코드)
    isin_code_name VARCHAR(100) NOT NULL, -- 국제 채권 식별 번호 명칭
    crno VARCHAR(13), -- 법인등록번호
    issuer_name VARCHAR(100) NOT NULL, -- 채권 발행인 명칭
    issue_date DATE NOT NULL, -- 채권 발행 일자
    issue_format_name VARCHAR(100) NOT NULL, -- 채권 발행 형태 명칭
    surface_interest_rate DECIMAL(15,10) NOT NULL, -- 채권 표면 이자율
    expired_date DATE NOT NULL, -- 채권 만기 일자
    securities_item_kind_code VARCHAR(4) NOT NULL, -- 유가증권 종목 종류 코드
    interest_change_code CHAR(1) NOT NULL, -- 금리 변동 구분 코드
    interest_type_code CHAR(1) NOT NULL, -- 채권 이자 유형 코드
    issue_currency_code VARCHAR(3) NOT NULL, -- 채권 발행 통화 코드
    price DECIMAL(15, 2), -- 채권 가격
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 레코드 생성 시간
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL, -- 레코드 업데이트 시간,
    FOREIGN KEY (securities_item_kind_code) REFERENCES bond_securities_item_kind(code),
    FOREIGN KEY (interest_change_code) REFERENCES bond_interest_change(code),
    FOREIGN KEY (interest_type_code) REFERENCES bond_interest_type(code),
    FOREIGN KEY (issuer_name) REFERENCES bond_issuer(name)
);