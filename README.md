لمبة
وصف المشروع :
منصة التعلم بين الأقران هي نظام تعليمي مبتكر عبر الإنترنت مصمم لسد فجوة المعرفة بين الطلاب من خلال تمكين التعلم بين الأقران. تتيح المنصة للطلاب أن يصبحوا مدرسين، ويشاركون خبراتهم ويرشدون زملائهم المتعلمين. كما تسهل إنشاء جلسات دراسية ودروس خصوصية وتبادل المعرفة بين الأقران في بيئة مرنة وتعاونية.

تتضمن الميزات الرئيسية ما يلي:

1- إدارة الدورات والجلسات: يمكن للمدرسين إنشاء وإدارة الدورات أو جلسات التدريس، مما يوفر فرص التعلم الفردية والجماعية.

2- بيئة تعليمية تفاعلية: يمكن للطلاب الانضمام إلى الجلسات أو شرائها، والمشاركة في مجموعات الدراسة، والوصول إلى الدروس الخصوصية عند الطلب.

3- سوق الموارد التعليمية: يتيح السوق المدمج للطلاب شراء أو بيع الكتب المستعملة ومواد الدراسة والموارد التعليمية الأخرى.

Project Description: Lamba 
Our Peer Learning Platform is an innovative online education system designed to bridge the knowledge gap among students by enabling peer-to-peer learning. The platform allows students to become tutors, sharing their expertise and guiding fellow learners. It facilitates the creation of study sessions, tutoring, and knowledge exchange between peers in a flexible, collaborative environment.
Key features include:
1- Course and Session Management: Tutors can create and manage courses or tutoring sessions, offering both one-on-one and group learning opportunities.
2- Interactive Learning Environment: Students can join or purchase sessions, participate in study groups, and access tutoring on-demand.
3- Marketplace for Educational Resources: A built-in marketplace enables students to buy or sell used books, study materials, and other educational resources.

---------------------------
Class Models 

Session
Video
ZoomMeeting
FaceToFace
Document
----------------------------
Relations : 

Document 
    @ManyToOne
    private Session session;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Tutor tutor;
FaceToFace
    @OneToOne
    private Session session;
    @ManyToOne
    private Tutor tutor;
    @ManyToOne
    private Student student;
Session
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> students;
    @ManyToOne
    private Tutor tutor;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL)
    private Set<Document> documents;
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL)
    private Set<Video> videos;
    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL)
    private ZoomMeeting zoomMeeting;
    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL)
    private FaceToFace faceToFace;
Video
    @ManyToOne
    private Session session;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
ZoomMeeting
    @OneToOne
    private Session session;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Tutor tutor;
-----------------------------
