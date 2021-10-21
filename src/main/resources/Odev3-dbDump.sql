--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

-- Started on 2021-10-21 03:38:19

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16481)
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id character varying NOT NULL,
    post_id character varying,
    author_id character varying,
    content character varying
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16488)
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.posts (
    id character varying,
    author_id character varying,
    post_title character varying,
    post_content character varying,
    post_date date
);


ALTER TABLE public.posts OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16495)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id character varying NOT NULL,
    email character varying,
    display_name character varying
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 3319 (class 0 OID 16481)
-- Dependencies: 209
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (id, post_id, author_id, content) FROM stdin;
1	1	2	comment
\.


--
-- TOC entry 3320 (class 0 OID 16488)
-- Dependencies: 210
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (id, author_id, post_title, post_content, post_date) FROM stdin;
1	1	title	body	2021-10-21
\.


--
-- TOC entry 3321 (class 0 OID 16495)
-- Dependencies: 211
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, email, display_name) FROM stdin;
1	ugur@dede.com	Ugurcan Dede
2	cagri@folksdev.com	Cagri Dursun
\.


--
-- TOC entry 3172 (class 2606 OID 16487)
-- Name: comments comments_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pk PRIMARY KEY (id);


--
-- TOC entry 3176 (class 2606 OID 16502)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 3173 (class 1259 OID 16493)
-- Name: posts_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX posts_id_uindex ON public.posts USING btree (id);


--
-- TOC entry 3174 (class 1259 OID 16500)
-- Name: user_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX user_id_uindex ON public."user" USING btree (id);


--
-- TOC entry 3178 (class 2606 OID 16513)
-- Name: comments comments_posts_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_posts_id_fk FOREIGN KEY (post_id) REFERENCES public.posts(id);


--
-- TOC entry 3177 (class 2606 OID 16503)
-- Name: comments comments_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fk FOREIGN KEY (author_id) REFERENCES public."user"(id);


--
-- TOC entry 3179 (class 2606 OID 16508)
-- Name: posts posts_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_user_id_fk FOREIGN KEY (author_id) REFERENCES public."user"(id);


-- Completed on 2021-10-21 03:38:19

--
-- PostgreSQL database dump complete
--

