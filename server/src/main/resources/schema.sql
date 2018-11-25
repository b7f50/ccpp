CREATE TABLE company (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    deleted timestamp without time zone,
    active boolean DEFAULT true NOT NULL,
    name character varying(255) NOT NULL,
    company_payment_package bigint
);

CREATE TABLE company_payment_package (
    id bigint NOT NULL,
    modified timestamp without time zone NOT NULL,
    version integer DEFAULT 0 NOT NULL,
    begin_date timestamp without time zone,
    duration bigint,
    expiration_date timestamp without time zone,
    identity_limit integer NOT NULL,
    quota_limit bigint NOT NULL
);
