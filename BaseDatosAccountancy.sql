PGDMP  	                    |            accountancy    16.2    16.0     #           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            $           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            %           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            &           1262    16399    accountancy    DATABASE     �   CREATE DATABASE accountancy WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Argentina.1252';
    DROP DATABASE accountancy;
                postgres    false            �            1259    16788    account    TABLE     �  CREATE TABLE public.account (
    number_account integer NOT NULL,
    amount_initial double precision,
    identification bigint,
    state character varying(255),
    type_account character varying(255),
    CONSTRAINT account_state_check CHECK (((state)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying])::text[]))),
    CONSTRAINT account_type_account_check CHECK (((type_account)::text = ANY ((ARRAY['CORRIENTE'::character varying, 'AHORRO'::character varying])::text[])))
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    16787    account_number_account_seq    SEQUENCE     �   CREATE SEQUENCE public.account_number_account_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.account_number_account_seq;
       public          postgres    false    216            '           0    0    account_number_account_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.account_number_account_seq OWNED BY public.account.number_account;
          public          postgres    false    215            �            1259    16799    movement    TABLE     �  CREATE TABLE public.movement (
    id integer NOT NULL,
    amount double precision,
    date timestamp(6) without time zone,
    type_movement character varying(255),
    value double precision,
    number_account integer,
    CONSTRAINT movement_type_movement_check CHECK (((type_movement)::text = ANY ((ARRAY['INGRESO'::character varying, 'EGRESO'::character varying])::text[])))
);
    DROP TABLE public.movement;
       public         heap    postgres    false            �            1259    16798    movement_id_seq    SEQUENCE     �   CREATE SEQUENCE public.movement_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.movement_id_seq;
       public          postgres    false    218            (           0    0    movement_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.movement_id_seq OWNED BY public.movement.id;
          public          postgres    false    217            �           2604    16791    account number_account    DEFAULT     �   ALTER TABLE ONLY public.account ALTER COLUMN number_account SET DEFAULT nextval('public.account_number_account_seq'::regclass);
 E   ALTER TABLE public.account ALTER COLUMN number_account DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    16802    movement id    DEFAULT     j   ALTER TABLE ONLY public.movement ALTER COLUMN id SET DEFAULT nextval('public.movement_id_seq'::regclass);
 :   ALTER TABLE public.movement ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218                      0    16788    account 
   TABLE DATA           f   COPY public.account (number_account, amount_initial, identification, state, type_account) FROM stdin;
    public          postgres    false    216   �                  0    16799    movement 
   TABLE DATA           Z   COPY public.movement (id, amount, date, type_movement, value, number_account) FROM stdin;
    public          postgres    false    218   2       )           0    0    account_number_account_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.account_number_account_seq', 4, true);
          public          postgres    false    215            *           0    0    movement_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.movement_id_seq', 14, true);
          public          postgres    false    217            �           2606    16797    account account_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (number_account);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    216            �           2606    16805    movement movement_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.movement
    ADD CONSTRAINT movement_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.movement DROP CONSTRAINT movement_pkey;
       public            postgres    false    218            �           2606    16806 $   movement fkglrhua9uqqy61ht4qdqi52kly    FK CONSTRAINT     �   ALTER TABLE ONLY public.movement
    ADD CONSTRAINT fkglrhua9uqqy61ht4qdqi52kly FOREIGN KEY (number_account) REFERENCES public.account(number_account);
 N   ALTER TABLE ONLY public.movement DROP CONSTRAINT fkglrhua9uqqy61ht4qdqi52kly;
       public          postgres    false    216    218    4746               U   x�3�4600�4426153��tt�s�t�
�t�q�2�D����;z �ss�c��5�4676�34��3�ZKL�b���� ���          �   x�m�KjC1@ѱ��n BYoJ'���uR�
�<4�[f�IBb����q0��������u^F��T�%x��-vL�;��80�6���D���	���5��6�vBDg���r�4� �+;��n�	����9��f��jA�ڵ�N1�ڃ<�1"�^i��ΏFA3��\������?s�|��kS���h�BJ��oc� ��x.     