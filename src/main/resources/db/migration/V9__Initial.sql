ALTER TABLE request
ADD CONSTRAINT UC_REQUEST_APPROVED
UNIQUE (REQUEST_BY, APPROVED_BY);