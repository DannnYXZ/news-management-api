--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4 (Debian 11.4-1)
-- Dumped by pg_dump version 11.5 (Debian 11.5-3sid2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: schema; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA schema;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: author; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.author (
                               id bigint NOT NULL,
                               name character varying(30) NOT NULL,
                               surname character varying(30) NOT NULL
);


--
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.author_id_seq OWNED BY public.author.id;


--
-- Name: news; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.news (
                             id bigint NOT NULL,
                             title character varying(200) NOT NULL,
                             short_text character varying(500) NOT NULL,
                             full_text character varying(2000) NOT NULL,
                             creation_date date NOT NULL,
                             modification_date date NOT NULL
);


--
-- Name: news_author; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.news_author (
                                    news_id bigint NOT NULL,
                                    author_id bigint NOT NULL
);


--
-- Name: news_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.news_id_seq OWNED BY public.news.id;


--
-- Name: news_tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.news_tag (
                                 news_id bigint NOT NULL,
                                 tag_id bigint NOT NULL
);


--
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
                              user_id bigint NOT NULL,
                              role_name character varying(30) NOT NULL
);


--
-- Name: tag; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tag (
                            id bigint NOT NULL,
                            name character varying(30) NOT NULL
);


--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."user" (
                               id bigint NOT NULL,
                               name character varying(20) NOT NULL,
                               surname character varying(20) NOT NULL,
                               login character varying(30) NOT NULL,
                               password character varying(30) NOT NULL
);


--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- Name: author; Type: TABLE; Schema: schema; Owner: -
--

CREATE TABLE schema.author (
                               id bigint NOT NULL,
                               name character varying(30) NOT NULL,
                               surname character varying(30) NOT NULL
);


--
-- Name: author_id_seq; Type: SEQUENCE; Schema: schema; Owner: -
--

CREATE SEQUENCE schema.author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: schema; Owner: -
--

ALTER SEQUENCE schema.author_id_seq OWNED BY schema.author.id;


--
-- Name: news; Type: TABLE; Schema: schema; Owner: -
--

CREATE TABLE schema.news (
                             id bigint NOT NULL,
                             title character varying(30) NOT NULL,
                             short_text character varying(100) NOT NULL,
                             full_text character varying(2000) NOT NULL,
                             creation_date date NOT NULL,
                             modification_date date NOT NULL
);


--
-- Name: news_author; Type: TABLE; Schema: schema; Owner: -
--

CREATE TABLE schema.news_author (
                                    news_id bigint NOT NULL,
                                    author_id bigint NOT NULL
);


--
-- Name: news_tag; Type: TABLE; Schema: schema; Owner: -
--

CREATE TABLE schema.news_tag (
                                 news_id bigint NOT NULL,
                                 tag_id bigint NOT NULL
);


--
-- Name: roles; Type: TABLE; Schema: schema; Owner: -
--

CREATE TABLE schema.roles (
                              user_id bigint NOT NULL,
                              role_name character varying(30) NOT NULL
);


--
-- Name: tag; Type: TABLE; Schema: schema; Owner: -
--

CREATE TABLE schema.tag (
                            id bigint NOT NULL,
                            name character varying(30) NOT NULL
);


--
-- Name: user; Type: TABLE; Schema: schema; Owner: -
--

CREATE TABLE schema."user" (
                               id bigint NOT NULL,
                               name character varying(20) NOT NULL,
                               surname character varying(20) NOT NULL,
                               login character varying(30) NOT NULL,
                               password character varying(30) NOT NULL
);


--
-- Name: author id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.author ALTER COLUMN id SET DEFAULT nextval('public.author_id_seq'::regclass);


--
-- Name: news id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_seq'::regclass);


--
-- Name: tag id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- Name: author id; Type: DEFAULT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.author ALTER COLUMN id SET DEFAULT nextval('schema.author_id_seq'::regclass);


--
-- Name: author author_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pk PRIMARY KEY (id);


--
-- Name: news_author news_author_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT news_author_pk PRIMARY KEY (news_id);


--
-- Name: news news_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pk PRIMARY KEY (id);


--
-- Name: news_tag news_tag_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT news_tag_pk UNIQUE (news_id, tag_id);


--
-- Name: tag tag_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pk PRIMARY KEY (id);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: author author_pk; Type: CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.author
    ADD CONSTRAINT author_pk PRIMARY KEY (id);


--
-- Name: news news_pk; Type: CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.news
    ADD CONSTRAINT news_pk PRIMARY KEY (id);


--
-- Name: tag tag_pk; Type: CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.tag
    ADD CONSTRAINT tag_pk PRIMARY KEY (id);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: news_author author_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES public.author(id) ON DELETE CASCADE;


--
-- Name: news_author news_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news_author
    ADD CONSTRAINT news_id FOREIGN KEY (news_id) REFERENCES public.news(id) ON DELETE CASCADE;


--
-- Name: news_tag news_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT news_id FOREIGN KEY (news_id) REFERENCES public.news(id) ON DELETE CASCADE;


--
-- Name: news_tag tag_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.news_tag
    ADD CONSTRAINT tag_id FOREIGN KEY (tag_id) REFERENCES public.tag(id) ON DELETE CASCADE;


--
-- Name: roles user_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: news_author author_id; Type: FK CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.news_author
    ADD CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES schema.author(id);


--
-- Name: news_author news_id; Type: FK CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.news_author
    ADD CONSTRAINT news_id FOREIGN KEY (news_id) REFERENCES schema.news(id);


--
-- Name: news_tag news_id; Type: FK CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.news_tag
    ADD CONSTRAINT news_id FOREIGN KEY (news_id) REFERENCES schema.news(id);


--
-- Name: news_tag tag_id; Type: FK CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.news_tag
    ADD CONSTRAINT tag_id FOREIGN KEY (tag_id) REFERENCES schema.tag(id);


--
-- Name: roles user_id; Type: FK CONSTRAINT; Schema: schema; Owner: -
--

ALTER TABLE ONLY schema.roles
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES schema."user"(id);


--
-- PostgreSQL database dump complete
--

SET search_path TO public;