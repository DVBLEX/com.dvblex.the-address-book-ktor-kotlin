CREATE TABLE contacts
(
    contactId        VARCHAR(100) PRIMARY KEY,
    firstName        VARCHAR(150) NOT NULL,
    lastName         VARCHAR(150) NOT NULL,
    contactNumbersId VARCHAR(100),
    emailAddressesId VARCHAR(100),
    FOREIGN KEY (contactNumbersId) REFERENCES contactNumbers(id),
    FOREIGN KEY (emailAddressesId) REFERENCES emailAddresses(id)
);

CREATE TABLE contactNumbers
(
    id              VARCHAR(50) PRIMARY KEY,
    number          VARCHAR(50) NOT NULL,
    contactId       VARCHAR(100),
    FOREIGN KEY (contactId) REFERENCES contacts(contactId)
);

CREATE TABLE emailAddresses
(
    id             VARCHAR(50) PRIMARY KEY,
    email          VARCHAR(50) NOT NULL,
    contactId      VARCHAR(100),
    FOREIGN KEY (contactId) REFERENCES contacts(contactId)
);