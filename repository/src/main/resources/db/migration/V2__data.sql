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
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.author (id, name, surname) FROM stdin;
13	Rivez	Thompson
12	Sara	Jaskson
11	Mandelbrot	Martin
8	Nick	Anderson
15	Bob	White
14	Kate	Lopez
1	Ziad	Smith
4	Kameo	Brown
5	Leonardo	Davis
3	Marchello	Jones
7	Zigmund	Rodriguez
2	Karamba	Johnson
9	John	Thomas
10	Christopher	Moore
6	Stefan	Garcia
16	Polly	Walker
17	Clark	Robinson
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.news (id, title, short_text, full_text, creation_date, modification_date) FROM stdin;
4	'Fox & Friends' on Nancy Pelosi's attempt to 'mind meld with AOC' at SOTU	"Fox & Friends" hosts on Nancy Pelosi's behavior at the State of the Union address.	video	2003-07-03	2003-07-03
12	Newt Gingrich on Iowa: 'Media is totally misreporting' results after 'rigged process'	Former House Speaker Newt Gingrich said on “Fox & Friends” on Thursday that media is "totally misreporting Iowa” and that Bernie Sanders, I-Vt., won the state's caucuses.	“Sanders got the most votes in the first round. He got the most votes in the second round. The only reason [former South Bend, Indiana Mayor Pete] Buttigieg is getting all this press is that the Iowa process is rigged.”	2020-02-14	2020-02-14
6	Former Obama finance adviser: 'I just don't recognize the Democratic Party right now'	Today's Democratic Party is unrecognizable to two-time former Obama National Finance Committee Member Don Peebles.	Appearing on "Fox & Friends" with host Steve Doocy, Peebles said that he was astonished by his party members' behavior at President Trump's State of the Union address.\nOn Tuesday evening House Speaker Nancy Pelosi, D-Calif., made waves when she unceremoniously tore up her copy of the president's speech at its conclusion. Now, Republicans are calling for ethics charges against her and even her expulsion from office.\nNEWT GINGRICH: PELOSI SHOULD BE KICKED OUT OF OFFICE FOR HER 'PETTY, CHILDISH' BEHAVIOR\n"Look, I just don't recognize the Democratic Party right now," Peebles said. "I mean, the party has turned so far-left. Also, to see members of Congress jointly dressing up in white as some form of protest or solidarity at the State of the Union address is astonishing."	2005-04-12	2005-04-12
2	Can Mitt Romney actually be expelled from the Republican Party?	President Trump speaks at National Prayer Breakfast, mentions Democrats impeaching him for "nothing."	President Trump speaks at National Prayer Breakfast, mentions Democrats impeaching him for "nothing."	2000-05-17	2000-05-17
3	Senate report: Obama administration was ‘frozen’ in combating 2016 Russian election meddling	The Obama administration was “frozen” in combating 2016 Russian election meddling, according to a new report released Thursday by the Senate Intelligence Committee.	“After discovering the existence, if not the full scope, of Russia’s election interference efforts in late 2016, the Obama Administration struggled to determine the appropriate response,” committee Chairman Richard Burr, R-N.C., said in a statement Thursday. “Frozen by ‘paralysis of analysis,’ hamstrung by constraints both real and perceived, Obama officials debated courses of action without truly taking one.”\n\nCommittee Ranking Member Mark Warner, D-Va., said he hoped the panel’s findings would “resonate with lawmakers, national security experts and the American public.”\n\n“There were many flaws with the U.S. response to the 2016 attack, but it’s worth noting that many of those were due to problems with our own system — problems that can and should be corrected,” Warner said.	2005-02-10	2005-02-10
11	Indian girl, 5, allegedly raped at US embassy in New Delhi: reports	A 5-year-old Indian girl was allegedly raped on the grounds of a U.S. embassy in the capital city of New Delhi, officials said.	The child, whose father is a housekeeper employed at the embassy, was alone playing outside Saturday morning when her neighbor, the 25-year-old son of another embassy employee, allegedly lured her inside his family’s living quarters and raped her, local police told PTI news agency.	2019-06-06	2019-06-06
10	ICE agent shoots man in face in New York: reports	A man in New York City was shot in the face Thursday morning during an incident that involved a U.S. Immigration and Customs Enforcement (ICE) agent, according to local media.	While serving the warrant to Avendando-Hernandez, his girlfriend's son, 26-year-old Eric Diaz, got into a fight with officers, WABC-TV reported. The agent fired his gun and struck Diaz in the face, according to local media.	2020-02-02	2020-02-02
8	Tomi Lahren rips anti-police protesters who trashed NYC over $2.75 subway fare: 'Are you out of your mind?'	While the rest of the country was gearing up for the Super Bowl, the Iowa Caucuses and the closing days of the Senate impeachment trial, in New York City a group of anti-police protesters were organizing a demonstration that alienated even some their most sympathetic potential allies.	"So what are they so enraged about?" she asked rhetorically. "They don't think they should have to pay the [subway] fare – of get this – $2.75 cents. And they don't want 500 new officers hired to police their indecent and unlawful behavior on and around the city transit system."\n"F*** your $2.75," read one of the protest organizers in a video posted on Twitter. "No cops on the MTA. Free transit. No harassment, period, and full accessibility."\nOn Jan. 31, thousands of demonstrators descended on New York City's Grand Central Terminal, one of Manhattan's biggest transportations hubs, and other subway stations around the city.	2018-02-14	2018-02-14
9	Tim Scott: Dems 'lose their minds' when Trump talks about helping minority communities	Democrats “lose their minds” at President Trump’s “laser-focus” on lifting America's “most vulnerable poor people," Sen. Tim Scott told “Fox & Friends" on Thursday.	“When he starts talking about the lowest Hispanic unemployment rate, when he starts talking about the highest increase in wages came from the poorest Americans who are working -- the story of the American comeback is a story that starts at the most vulnerable, fragile people on the economic ladder and moves up."	2016-07-13	2016-07-13
5	Biden's poor Iowa showing dramatically ups the stakes in New Hampshire	"I’m not going to sugarcoat it. We took a gut punch in Iowa," the former vice president admitted as he kicked off a campaign event in New Hampshire on Wednesday.	With Sens. Bernie Sanders of Vermont and Elizabeth Warren of Massachusetts hailing from neighboring states and enjoying a degree of home-field advantage, the Biden campaign has always seen Nevada and South Carolina -- which directly follow New Hampshire and have a diverse electorate -- as better prospects. And South Carolina, with a majority black electorate in the Democratic presidential primary, has long been considered Biden's firewall.	2004-02-19	2004-02-19
1	Trump takes shot at Nancy Pelosi	As Sen. Mitt Romney, R-Utah, voted Wednesday to convict President Trump on one of the two articles of impeachment against him – abuse of power – Trump's son Donald Trump Jr. was already calling for Romney's expulsion from the Republican Party.	video	2002-07-27	2002-07-27
7	Yovanovitch sounds off after Trump acquittal: 'I have seen dictatorships'	Elizabeth Smart, the woman who was abducted when she was only 14, revealed she was sexually assaulted last year while aboard a Delta Air Lines flight.	“I had been asleep and all of a sudden I woke up because I felt someone’s hand rubbing in between my legs on my inner thigh,” she told King. “The last time someone touched me without my say-so was when I was kidnapped. And I froze. I didn’t know what to do. And I speak to other women about this.”	2000-02-01	2000-02-01
    16	Iowa Democrats find target to blame for caucus fiasco: Trump supporters	It's Trump's fault!	Red-faced Iowa Democrats have zeroed in on a new culprit for the botched caucuses that may cost the Hawkeye State its time-honored role as the kickoff to presidential races. After first blaming an app for the inability to declare Monday night, a problem that still lingers even as frustrated candidates have packed up and moved on to New Hampshire for the nation's first primary, the state party is blaming the president's supporters.	2018-05-10	2018-05-10
    14	2021 Mercedes-Benz Weekender camper van is ready for the great outdoors	The second-row seat also converts into a two-person bed, and the front captain’s chairs swivel 180-degrees to face the passenger compartment while the vehicle is parked.	The Metris minivan-based Weekender has a roof-mounted tent that’s accessed from inside the cabin and equipped with a two-person memory foam mattress and USB charging ports for lights and accessories. The tent is protected by a solid cover with roof racks for skis, surfboards, etc.	2020-07-09	2020-07-09
    17	Larry King says 26-year age gap, religion took 'its toll' on marriage, ultimately led to divorce	Larry King is speaking out about the downfall of his marriage to Shawn Southwick, saying religion and their 26-year-year age gap eventually took "its toll" on their relationship.	“I got married a lot,” he said. “But in my head, I’m not a marrying guy. When I grew up, nobody lived together. If you fell in love, you got married. And so I married the ones that I loved. But what I loved at 20 is not what I loved at 30 and what I loved at 30 is not what I loved at 40.”	2018-02-13	2018-02-13
    15	Insurance company accuses Doherty of using cancer diagnosis to get payout	The "90210" actress' attorney, Devin McRae, told Fox News he finds the insurance company's most recent argument "quite appalling."	McRae disputed several claims made by State Farm in their Wednesday court filing. The insurance company accused Doherty on Wednesday of planning to "garner sympathy by her contention that State Farm must rebuild her entire house" when the case heads to trial, Page Six reported.	2020-02-17	2020-02-17
    13	Jay-Z reveals Kobe Bryant's last words to him before NBA star's death	Jay-Z is opening up about the final conversation he had with Kobe Bryant just weeks before the NBA star's untimely death.	Jay-Z discussed his relationship with the Los Angeles Lakers legend during a Q&A session at New York City's Columbia University on Tuesday. Bryant was 41 years old when he died in a helicopter crash on Jan. 26 along with this 13-year-old daughter, Gianna, and seven other victims.	2018-06-07	2018-06-07
    \.


    --
-- Data for Name: news_author; Type: TABLE DATA; Schema: public; Owner: -
--

    COPY public.news_author (news_id, author_id) FROM stdin;
9	9
16	2
8	10
6	12
14	4
5	13
13	5
10	8
17	1
2	16
7	11
1	17
15	3
4	14
12	6
3	15
11	7
\.


--
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.tag (id, name) FROM stdin;
2	Dogs
7	Someone injured
1	Travelling
8	Lifestyle
4	Critical
6	Cats
5	MIT
9	Wedding
10	Food
11	UFO
12	Strange
13	Flood
14	Health
15	Politics
16	Music
17	Art
18	Extreme
19	Cars
20	Entertainment
21	Sports
3	Salami
\.


--
-- Data for Name: news_tag; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.news_tag (news_id, tag_id) FROM stdin;
1	17
2	16
3	15
4	14
5	13
6	12
7	11
8	10
9	9
10	8
11	7
12	6
13	5
14	4
15	3
16	2
17	1
1	3
1	10
2	8
2	9
3	10
3	8
4	11
4	18
5	3
5	2
17	11
17	10
14	2
14	1
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public."user" (id, name, surname, login, password) FROM stdin;
1	Rivez	Thompson	asd	3434234
2	Sara	Jaskson	39812p3jeioj0p981	39282do9n98n2d
3	Mandelbrot	Martin	sijdopi2uimod	23829nd9i2qd
4	Nick	Anderson	23829nd9i2qd	sijdopi2uimod
5	Bob	White	39282do9n98n2d	39812p3jeioj0p981
6	Ziad	Smith	sd23dsfsdf\n	34
7	Kameo	Brown	we4	2313123
8	Leonardo	Davis	3333dd3	sde33d
9	Marchello	Jones	32423t	sdfsdf
10	Zigmund	Rodriguez	we	34234
11	Karamba	Johnson	2313123	we4
13	Stefan	Garcia	3434234	xczxc
12	Christopher	Moore	34	sd23dsfsdf\n
14	Polly	Walker	sdfsdf	32423t
16	Kate	Lopez	sde33d	3333dd3
15	John	Thomas	34234	ewew
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.roles (user_id, role_name) FROM stdin;
1	ADMIN
2	USER
3	MODERATOR
4	USER
5	MODERATOR
6	USER
7	MANAGER
8	VIP
9	USER
10	MANAGER
11	USER
12	MODERATOR
13	USER
14	USER
15	MODERATOR
16	MANAGER
\.

--
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.author_id_seq', 17, true);


--
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.news_id_seq', 30, true);


--
-- Name: tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tag_id_seq', 21, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_id_seq', 17, true);


--
-- PostgreSQL database dump complete
--

