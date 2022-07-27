--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

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
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    role character varying(31) NOT NULL,
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(256) NOT NULL,
    index bigint,
    school_class_id bigint
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_id_seq OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;


--
-- Name: attendance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attendance (
    id bigint NOT NULL,
    status integer NOT NULL,
    meeting_id bigint,
    student_id bigint
);


ALTER TABLE public.attendance OWNER TO postgres;

--
-- Name: attendance_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.attendance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.attendance_id_seq OWNER TO postgres;

--
-- Name: attendance_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.attendance_id_seq OWNED BY public.attendance.id;


--
-- Name: lesson; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lesson (
    id bigint NOT NULL,
    school_class_id bigint NOT NULL,
    subject_id bigint NOT NULL,
    teacher_id bigint NOT NULL
);


ALTER TABLE public.lesson OWNER TO postgres;

--
-- Name: lesson_block; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lesson_block (
    id bigint NOT NULL,
    duration integer NOT NULL,
    start_time character varying(255) NOT NULL,
    week_day character varying(255) NOT NULL,
    lesson_id bigint NOT NULL,
    CONSTRAINT lesson_block_duration_check CHECK ((duration >= 0))
);


ALTER TABLE public.lesson_block OWNER TO postgres;

--
-- Name: lesson_block_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lesson_block_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lesson_block_id_seq OWNER TO postgres;

--
-- Name: lesson_block_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lesson_block_id_seq OWNED BY public.lesson_block.id;


--
-- Name: lesson_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lesson_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lesson_id_seq OWNER TO postgres;

--
-- Name: lesson_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lesson_id_seq OWNED BY public.lesson.id;


--
-- Name: mark; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mark (
    id bigint NOT NULL,
    description character varying(255),
    label character varying(255) NOT NULL,
    "timestamp" timestamp without time zone,
    value integer NOT NULL,
    weight integer NOT NULL,
    lesson_id bigint NOT NULL,
    student_id bigint NOT NULL,
    teacher_id bigint NOT NULL,
    CONSTRAINT mark_value_check CHECK (((value <= 6) AND (value >= 1))),
    CONSTRAINT mark_weight_check CHECK ((weight >= 0))
);


ALTER TABLE public.mark OWNER TO postgres;

--
-- Name: mark_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mark_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mark_id_seq OWNER TO postgres;

--
-- Name: mark_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.mark_id_seq OWNED BY public.mark.id;


--
-- Name: meeting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meeting (
    id bigint NOT NULL,
    description character varying(255),
    room character varying(255) NOT NULL,
    start_time character varying(255) NOT NULL,
    "time" timestamp without time zone NOT NULL,
    topic character varying(255),
    week_day character varying(255) NOT NULL,
    lesson_id bigint NOT NULL,
    teacher_id bigint NOT NULL
);


ALTER TABLE public.meeting OWNER TO postgres;

--
-- Name: meeting_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meeting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meeting_id_seq OWNER TO postgres;

--
-- Name: meeting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meeting_id_seq OWNED BY public.meeting.id;


--
-- Name: parent_student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.parent_student (
    parent_id bigint NOT NULL,
    student_id bigint NOT NULL
);


ALTER TABLE public.parent_student OWNER TO postgres;

--
-- Name: school_class; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.school_class (
    id bigint NOT NULL,
    class_name character varying(255) NOT NULL,
    profile character varying(255) NOT NULL,
    homeroom_teacher_id bigint NOT NULL
);


ALTER TABLE public.school_class OWNER TO postgres;

--
-- Name: school_class_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.school_class_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.school_class_id_seq OWNER TO postgres;

--
-- Name: school_class_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.school_class_id_seq OWNED BY public.school_class.id;


--
-- Name: subject; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subject (
    id bigint NOT NULL,
    subject_name character varying(255) NOT NULL
);


ALTER TABLE public.subject OWNER TO postgres;

--
-- Name: subject_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subject_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subject_id_seq OWNER TO postgres;

--
-- Name: subject_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subject_id_seq OWNED BY public.subject.id;


--
-- Name: teacher_subject; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teacher_subject (
    teacher_id bigint NOT NULL,
    subject_id bigint NOT NULL
);


ALTER TABLE public.teacher_subject OWNER TO postgres;

--
-- Name: account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);


--
-- Name: attendance id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance ALTER COLUMN id SET DEFAULT nextval('public.attendance_id_seq'::regclass);


--
-- Name: lesson id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson ALTER COLUMN id SET DEFAULT nextval('public.lesson_id_seq'::regclass);


--
-- Name: lesson_block id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson_block ALTER COLUMN id SET DEFAULT nextval('public.lesson_block_id_seq'::regclass);


--
-- Name: mark id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark ALTER COLUMN id SET DEFAULT nextval('public.mark_id_seq'::regclass);


--
-- Name: meeting id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting ALTER COLUMN id SET DEFAULT nextval('public.meeting_id_seq'::regclass);


--
-- Name: school_class id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.school_class ALTER COLUMN id SET DEFAULT nextval('public.school_class_id_seq'::regclass);


--
-- Name: subject id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject ALTER COLUMN id SET DEFAULT nextval('public.subject_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (role, id, first_name, last_name, login, password, index, school_class_id) FROM stdin;
ROLE_TEACHER	1	Pan	Kleks	mrKleks	$2a$10$6NlC.wtywNIcI2ijAdaczep6rgFCwS7xtyLlz79UjMYW4ZioFUrhm	\N	\N
ROLE_TEACHER	2	Jan	Burczymucha	JanuszMucha	$2a$10$.MalvVoJeIGHbkuLccKFP.dEYuQJkho6UgnGAQd.ggtwOpkuvslM2	\N	\N
ROLE_TEACHER	3	Anna	Kowalska	miniAnia	$2a$10$V.9Cxd4RXjr/zuiV96tvwu/5vftJzmwjHzDnmKwDS0qS6u0D7F9ly	\N	\N
ROLE_STUDENT	4	Adas	Niezgodka	adas69PL	$2a$10$uxeThbkSFVI9aIkNSnVAlejktuV2joE49scO92nY4GwF2yQ7DiTDq	1	1
ROLE_STUDENT	5	Karolina	Koralowa	krolowaKorali	$2a$10$a8CxoRwZlcj5ULWn4GBFiOTk4LKmDPKYUm4WH5OT9NLV4creAEWYW	2	1
ROLE_STUDENT	6	Michal	Bak	bananowySamuraj	$2a$10$VJpxWGju/ZYv0.BPuiKUJ..RdH0MT9pqEW./SSL1dguwfzXOGC4FC	3	1
ROLE_STUDENT	7	Jerzy	Andrzejewski	ShimazuAmarasu	$2a$10$watZf1xxnuCJuVUGV/xW.umi5IDLMpHvsdCED9mT5jg4kj.eAPNxu	4	1
ROLE_STUDENT	8	Michal	Szpak	Wielkooki	$2a$10$.RGNfc6r0lT1UNZZxFSn.ubtEsKvrO1IqR6qe0TmrJsPrbiZDrDCG	5	1
ROLE_STUDENT	9	Lola	Bola	wienerBerlin	$2a$10$duQBUvK1ozSYSSu4RIzYw.o2TdCmdL63trS/yppJG6zwn1LGcb/3q	6	2
ROLE_STUDENT	10	Bugs	Bunny	DoktorekPerkusista	$2a$10$OKUr9NMvHoQ15QoWZE89fedsXGHQK4Jo7jEBVcvLd/Bec5KKPpPrm	7	2
ROLE_PARENT	11	Leosia	Yung	YungStar	$2a$10$dCRuzZy6KgEUHpVN3tr.cOPlI0dzz60yfo35XC03epepXHBKJDE2C	\N	\N
ROLE_PARENT	12	Leopold	Staff	LeoRzeczy	$2a$10$vXpeP1iCnTNNxqmPs9YUh.qAzUqKLIZUEX/8RON888hNSGBMom9xG	\N	\N
\.


--
-- Data for Name: attendance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attendance (id, status, meeting_id, student_id) FROM stdin;
\.


--
-- Data for Name: lesson; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lesson (id, school_class_id, subject_id, teacher_id) FROM stdin;
1	1	1	1
2	1	2	1
3	2	3	3
\.


--
-- Data for Name: lesson_block; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lesson_block (id, duration, start_time, week_day, lesson_id) FROM stdin;
1	45	9:00	FRIDAY	1
2	45	11:00	THURSDAY	2
3	45	13:00	WEDNESDAY	2
4	45	15:00	TUESDAY	2
5	45	13:00	MONDAY	3
6	45	15:00	FRIDAY	1
\.


--
-- Data for Name: mark; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mark (id, description, label, "timestamp", value, weight, lesson_id, student_id, teacher_id) FROM stdin;
1	ocena za zadanie z krzywej beziera	Zadanie	2022-07-27 20:37:40.546	2	5	1	4	1
2	ocena za zadanie z krzywej beziera	Zadanie2	2022-07-27 20:37:40.562	3	3	1	4	1
\.


--
-- Data for Name: meeting; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meeting (id, description, room, start_time, "time", topic, week_day, lesson_id, teacher_id) FROM stdin;
1	\N	L3	09:00	2022-07-29 09:00:00	Lekcja 1: Krzywa Beziera	FRIDAY	1	1
2	\N	L3	11:00	2022-07-28 11:00:00	Lekcja2: krzywa B-spline	THURSDAY	1	1
3	\N	S2	13:00	2022-07-27 13:00:00	Lekcja 1: Dlaczego Mickiewicz wielkim poetą był	WEDNESDAY	2	1
4	\N	S2	15:00	2022-07-26 15:00:00	Lekcja 2: Ferdydurke	TUESDAY	2	1
5	\N	S3	13:00	2022-07-26 13:00:00	Lekcja 3: Aaaaaaaaaaaaaaaaaa	TUESDAY	2	1
6	\N	Ulica	13:00	2022-07-25 13:00:00	Lekcja 1: Zawracanie na rondze	MONDAY	3	1
7	\N	L3	11:00	2022-08-04 11:00:00	Lekcja2: krzywa B-spline	THURSDAY	1	1
8	\N	S2	13:00	2022-08-03 13:00:00	Lekcja 1: Dlaczego Mickiewicz wielkim poetą był	WEDNESDAY	2	1
9	\N	S2	15:00	2022-08-02 15:00:00	Lekcja 2: Ferdydurke	TUESDAY	2	1
10	\N	S3	13:00	2022-08-02 13:00:00	Lekcja 3: Aaaaaaaaaaaaaaaaaa	TUESDAY	2	1
11	\N	Ulica	13:00	2022-08-01 13:00:00	Lekcja 1: Zawracanie na rondze	MONDAY	3	1
\.


--
-- Data for Name: parent_student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.parent_student (parent_id, student_id) FROM stdin;
11	7
11	8
11	9
11	4
12	6
12	5
12	10
12	4
\.


--
-- Data for Name: school_class; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.school_class (id, class_name, profile, homeroom_teacher_id) FROM stdin;
1	Kleks class	Magic-Creativity	1
2	Burczymuszki	Latanie i burczenie	3
\.


--
-- Data for Name: subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subject (id, subject_name) FROM stdin;
1	Math
2	Polish Language
3	Driving class
\.


--
-- Data for Name: teacher_subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teacher_subject (teacher_id, subject_id) FROM stdin;
1	2
1	1
2	3
\.


--
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_id_seq', 12, true);


--
-- Name: attendance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.attendance_id_seq', 1, false);


--
-- Name: lesson_block_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lesson_block_id_seq', 6, true);


--
-- Name: lesson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lesson_id_seq', 3, true);


--
-- Name: mark_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mark_id_seq', 2, true);


--
-- Name: meeting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.meeting_id_seq', 11, true);


--
-- Name: school_class_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.school_class_id_seq', 2, true);


--
-- Name: subject_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subject_id_seq', 3, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: attendance attendance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT attendance_pkey PRIMARY KEY (id);


--
-- Name: lesson_block lesson_block_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson_block
    ADD CONSTRAINT lesson_block_pkey PRIMARY KEY (id);


--
-- Name: lesson lesson_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT lesson_pkey PRIMARY KEY (id);


--
-- Name: mark mark_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT mark_pkey PRIMARY KEY (id);


--
-- Name: meeting meeting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting
    ADD CONSTRAINT meeting_pkey PRIMARY KEY (id);


--
-- Name: parent_student parent_student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parent_student
    ADD CONSTRAINT parent_student_pkey PRIMARY KEY (parent_id, student_id);


--
-- Name: school_class school_class_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.school_class
    ADD CONSTRAINT school_class_pkey PRIMARY KEY (id);


--
-- Name: subject subject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT subject_pkey PRIMARY KEY (id);


--
-- Name: teacher_subject teacher_subject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher_subject
    ADD CONSTRAINT teacher_subject_pkey PRIMARY KEY (teacher_id, subject_id);


--
-- Name: lesson fk62vogrd9ewhu7udvhl7c62e81; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT fk62vogrd9ewhu7udvhl7c62e81 FOREIGN KEY (school_class_id) REFERENCES public.school_class(id);


--
-- Name: meeting fk770n95cfl6l2161l7l1busw97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting
    ADD CONSTRAINT fk770n95cfl6l2161l7l1busw97 FOREIGN KEY (teacher_id) REFERENCES public.account(id);


--
-- Name: parent_student fk7wjgg62jl889kts75a690y5q2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parent_student
    ADD CONSTRAINT fk7wjgg62jl889kts75a690y5q2 FOREIGN KEY (parent_id) REFERENCES public.account(id);


--
-- Name: lesson fk7ydr23s8y9j6lip5qrngoymx4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT fk7ydr23s8y9j6lip5qrngoymx4 FOREIGN KEY (subject_id) REFERENCES public.subject(id);


--
-- Name: attendance fk89vocls01emx9t1qgvre1cnve; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT fk89vocls01emx9t1qgvre1cnve FOREIGN KEY (meeting_id) REFERENCES public.meeting(id);


--
-- Name: teacher_subject fkdnhs9kxdlnrvhq5k111c87pna; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher_subject
    ADD CONSTRAINT fkdnhs9kxdlnrvhq5k111c87pna FOREIGN KEY (subject_id) REFERENCES public.subject(id);


--
-- Name: lesson_block fkeairo2ecw06whq3nnu053qiyt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson_block
    ADD CONSTRAINT fkeairo2ecw06whq3nnu053qiyt FOREIGN KEY (lesson_id) REFERENCES public.lesson(id);


--
-- Name: teacher_subject fkerce91i1gn4roqc4lkiunf2vr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher_subject
    ADD CONSTRAINT fkerce91i1gn4roqc4lkiunf2vr FOREIGN KEY (teacher_id) REFERENCES public.account(id);


--
-- Name: mark fkfunurp183t0i5tpgun9h1rb7x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT fkfunurp183t0i5tpgun9h1rb7x FOREIGN KEY (teacher_id) REFERENCES public.account(id);


--
-- Name: account fkhbqyv6sx9eca6vsfkx8k2pxl3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkhbqyv6sx9eca6vsfkx8k2pxl3 FOREIGN KEY (school_class_id) REFERENCES public.school_class(id);


--
-- Name: school_class fkn901afvfcolvlgq9m7pclb3nb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.school_class
    ADD CONSTRAINT fkn901afvfcolvlgq9m7pclb3nb FOREIGN KEY (homeroom_teacher_id) REFERENCES public.account(id);


--
-- Name: lesson fkoydsvmews0pja10sjlor5kfi6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT fkoydsvmews0pja10sjlor5kfi6 FOREIGN KEY (teacher_id) REFERENCES public.account(id);


--
-- Name: mark fkqw2aqrsqut82rwjb469mpyari; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT fkqw2aqrsqut82rwjb469mpyari FOREIGN KEY (lesson_id) REFERENCES public.lesson(id);


--
-- Name: mark fkqyraf2p7ow8xe4vho0akvk34p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mark
    ADD CONSTRAINT fkqyraf2p7ow8xe4vho0akvk34p FOREIGN KEY (student_id) REFERENCES public.account(id);


--
-- Name: attendance fkrdfam700yucp9ynlgp1nw4goo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT fkrdfam700yucp9ynlgp1nw4goo FOREIGN KEY (student_id) REFERENCES public.account(id);


--
-- Name: parent_student fkrs61altcvrcrld23rci0r18bi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.parent_student
    ADD CONSTRAINT fkrs61altcvrcrld23rci0r18bi FOREIGN KEY (student_id) REFERENCES public.account(id);


--
-- Name: meeting fkswe5qg4wnvafnksruxrja4rmp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting
    ADD CONSTRAINT fkswe5qg4wnvafnksruxrja4rmp FOREIGN KEY (lesson_id) REFERENCES public.lesson(id);


--
-- PostgreSQL database dump complete
--

