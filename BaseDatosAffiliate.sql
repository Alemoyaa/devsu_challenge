PGDMP  +                    |         	   affiliate    16.2    16.0 
               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16398 	   affiliate    DATABASE     �   CREATE DATABASE affiliate WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Argentina.1252';
    DROP DATABASE affiliate;
                postgres    false            �            1259    16777    customer    TABLE     �  CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    identification bigint NOT NULL,
    address character varying(255),
    age integer,
    gender character varying(255),
    name character varying(255),
    phone character varying(255),
    password character varying(255),
    state character varying(255),
    CONSTRAINT customer_state_check CHECK (((state)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying])::text[])))
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    16736    customer_seq    SEQUENCE     v   CREATE SEQUENCE public.customer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.customer_seq;
       public          postgres    false                      0    16777    customer 
   TABLE DATA           s   COPY public.customer (customer_id, identification, address, age, gender, name, phone, password, state) FROM stdin;
    public          postgres    false    216   ^                  0    0    customer_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.customer_seq', 201, true);
          public          postgres    false    215            �           2606    16784    customer customer_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id, identification);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    216    216            �           2606    16786 %   customer uk_jt63q2suy91q2uch0ll9wcxx5 
   CONSTRAINT     g   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT uk_jt63q2suy91q2uch0ll9wcxx5 UNIQUE (customer_id);
 O   ALTER TABLE ONLY public.customer DROP CONSTRAINT uk_jt63q2suy91q2uch0ll9wcxx5;
       public            postgres    false    216               �   x�m�A
�@����S���8��b
E�h�f��L(v�Jr#������4��B����s���D:���ڹ�!BAv��E�Ic�>@Y]��A e�W@�J�eմ!P�<pX0x����krҙ����C�ph����#�f��W��=B��A�     