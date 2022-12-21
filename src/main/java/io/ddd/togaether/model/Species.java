package io.ddd.togaether.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * create on 2022/12/11. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Species {
  GORDON_SETTER("고든 세터", "Gordon setter", "ㄱ"),
  COTON_DE_TULEAR("꼬똥 드 툴레아", "Coton de tulear","ㄱ"),
  GOLDEN_DOODLE("골든 두들", "Golden doodle","ㄱ"),
  GOLDEN_RETRIEVER("골든 리트리버", "Golden retriever","ㄱ"),
  GREAT_DANE("그레이트 데인", "Great dane","ㄱ"),
  GREATER_SWISS_MOUNTAIN_DOG("그레이트 스위스 마운틴 도그", "Greater swiss mountain dog","ㄱ"),
  GREAT_PYRENEES("그레이트 피레니즈", "Great pyrenees","ㄱ"),
  GREY_HOUND("그레이 하운드", "Grey hound","ㄱ"),
  GREENLAND_DOG("그린란드 견", "Greenland dog","ㄱ"),
  GLEN_OF_IMAAL_TERRIER("글렌 오브 이말 테리어", "Glen of imaal terrier","ㄱ"),
  KISHU_INU("기슈견", "Kishu inu","ㄱ"),
  NEOPOLITAN_MASTIFF("나폴리탄 마스티프", "Neopolitan mastiff","ㄴ"),
  NORWEGIAN_BUHUND("노르웨지안 부훈트", "Norwegian buhund","ㄴ"),
  NORWEGIAN_ELKHOUND("노르웨이 엘크 하운드", "Norwegian elkhound","ㄴ"),
  NORWICH_TERRIER("노리치 테리어", "Norwich terrier","ㄴ"),
  Nova_Scotia_Duck_Tolling_Retriever("노바 스코셔 덕 톨링 레트리버", "Nova scotia duck tolling retriever","ㄴ"),
  NEWFOUNDLAND("뉴펀들랜드", "Newfoundland","ㄴ"),
  DACHSHUND("닥스훈트", "Dachshund","ㄷ"),
  DALMATIAN("달마시안", "Dalmatian","ㄷ"),
  Dandie_dinmont_terrier("댄디 딘몬트 테리어", "Dandie dinmont terrier","ㄷ"),
  DOGO_CANARIO("도고 까나리오", "Dogo Canario","ㄷ"),
  Dogo_Argentino("도고 아르헨티노", "Dogo argentino","ㄷ"),
  DOGUE_DE_BORDEAUX("도그 드 보르도", "Dogue de bordeaux","ㄷ"),
  DOBERMAN_PINSCHER("도베르만 핀셔", "Doberman pinscher","ㄷ"),
  Tosa("도사견", "Tosa","ㄷ"),
  DONGGYEONG_DOG("동경이", "Donggyeong dog","ㄷ"),
  LAGOTTO_ROMAGNOLO("라고토 로마뇰로", "Lagotto romagnolo","ㄹ"),
  LHASA_APSO("라사압소", "Lhasa apso","ㄹ"),
  RAFEIRO_DO_ALENTEJO("라페이로 도 알렌테조", "Rafeiro do alentejo","ㄹ"),
  LAPPONIAN_HERDER("라포니안 허더", "Lapponian herder","ㄹ"),
  LABRADOR_RETRIEVER("래브라도 리트리버", "Labrador retriever","ㄹ"),
  LEONBERGER("레온베르거", "Leonberger","ㄹ"),
  LAKELAND_TERRIER("레이크랜드 테리어", "Lakeland terrier","ㄹ"),
  RHODESIAN_RIDGEBACK("로디지아 리지백", "Rhodesian ridgeback","ㄹ"),
  LOWCHEN("로첸", "Lowchen","ㄹ"),
  ROTTWEILER("로트와일러", "Rottweiler","ㄹ"),
  MASTIFF("마스티프", "Mastiff","ㅁ"),
  MANCHESTER_TERRIER("맨체스터 테리어", "Manchester terrier","ㅁ"),
  MALTESE("말티즈", "Maltese","ㅁ"),
  MINIATURE_BULL_TERRIER("미니어처 불 테리어", "Miniature bull terrier","ㅁ"),
  MINIATURE_SCHNAUZER("미니어처 슈나우저", "Miniature schnauzer","ㅁ"),
  MINIATURE_PINSCHER("미니어처 핀셔", "Miniature pinscher","ㅁ"),
  MALTIPOO("말티푸", "Maltipoo","ㅁ"),
  BASENJI("바센지", "Basenji","ㅂ"),
  BASSET_HOUND("바셋 하운드", "Basset hound","ㅂ"),
  BERNESE_MOUNTAIN_DOG("버니즈 마운틴 도그", "Bernese mountain dog","ㅂ"),
  BEDLINGTON_TERRIER("베들링턴 테리어", "Bedlington terrier","ㅂ"),
  BALBARI("발바리", "Balbari","ㅂ"),
  BELGIAN_MALINOIS("벨기에 말리노이즈", "Belgian malinois","ㅂ"),
  BELGIAN_TERVUREN("벨기에 테뷰런", "Belgian tervuren","ㅂ"),
  BELGIAN_GRIFFON("벨지안 그리펀", "Belgian griffon","ㅂ"),
  BELGIAN_SHEEPDOG("벨지언 쉽독", "Belgian sheepdog","ㅂ"),
  BORDER_COLLIE("보더 콜리", "Border collie","ㅂ"),
  BORDER_TERRIER("보더 테리어", "Border terrier","ㅂ"),
  BORDEAUX_MASTIFF("보르도 마스티프", "Bordeaux mastiff","ㅂ"),
  BORZOI("보르조이", "Borzoi","ㅂ"),
  BOUVIER_DES_FLANDRES("보비에 드 플랜더스", "Bouvier des flandres","ㅂ"),
  BEAUCERON("보스롱", "Beauceron","ㅂ"),
  BOSTON_TERRIER("보스턴 테리어", "Boston terrier","ㅂ"),
  BOXER("복서", "boxer","ㅂ"),
  BOLOGNESE("볼로네즈", "Bolognese","ㅂ"),
  BULGAE("불개", "Bulgae","ㅂ"),
  BULLDOG("불도그", "Bulldog","ㅂ"),
  BULLY_KUTTA("불리 쿠타", "Bully kutta","ㅂ"),
  BULL_MASTIFF("불 마스티프", "Bull mastiff","ㅂ"),
  BULL_TERRIER("불 테리어", "Bull terrier","ㅂ"),
  BRUSSELS_GRIFFON("브뤼셀 그리펀", "Brussels griffon","ㅂ"),
  BRIARD("브리어드", "Briard","ㅂ"),
  BRITTANY("브리타니", "Brittany","ㅂ"),
  BLACK_RUSSIAN_TERRIER("블랙 러시안 테리어", "Black russian terrier","ㅂ"),
  BLACK_AND_TAN_COONHOUND("블랙 앤드 탄 쿤하운드", "Black and tan coonhound","ㅂ"),
  BLOOD_HOUND("블러드 하운드", "Blood hound","ㅂ"),
  BEAGLE("비글", "beagle","ㅂ"),
  BICHON_FRISE("비숑 프리제", "Bichon frise","ㅂ"),
  BEARDED_COLLIE("비어디드 콜리", "Bearded collie","ㅂ"),
  VIZSLA("비즐라", "Vizsla","ㅂ"),
  PAPILLON("빠삐용", "papillon","ㅂ"),
  SAMOYED_DOG("사모예드", "Samoyed dog","ㅅ"),
  SARPLANINAC("사플라니낙", "Sarplaninac","ㅅ"),
  SALUKI("살루키", "Saluki","ㅅ"),
  SAPSAL_DOG("삽살개", "sapsal dog","ㅅ"),
  SHAR_PEI("샤페이", "Shar pei","ㅅ"),
  SUSSEX_SPANIEL("서식스 스패니얼", "Sussex spaniel","ㅅ"),
  SAINT_BERNARD("세인트 버나드", "Saint bernard","ㅅ"),
  SHETLAND_SHEEPDOG("셰틀랜드 쉽독", "Shetland sheepdog","ㅅ"),
  SOFT_COATED_WHEATEN_TERRIER("소프트 코티드 휘튼 테리어", "Soft coated wheaten terrier","ㅅ"),
  XOLOITZ_CUINTLE("솔로이츠 쿠인틀레", "xoloitz cuintle","ㅅ"),
  SMOOTH_FOX_TERRIER("스무드 폭스 테리어", "Smooth fox terrier","ㅅ"),
  SWEDISH_VALLHUND("스웨디쉬 발훈트", "Swedish vallhund","ㅅ"),
  SKYE_TERRIER("스카이 테리어", "Skye terrier","ㅅ"),
  SCOTTISH_DEERHOUND("스코티시 디어하운드", "Scottish deerhound","ㅅ"),
  SCOTTISH_TERRIER("스코티시 테리어", "Scottish terrier","ㅅ"),
  SCHIPPERKE("스키퍼키", "Schipperke","ㅅ"),
  STAFFORDSHIRE_BULL_TERRIER("스태퍼드셔 불 테리어", "Staffordshire bull terrier","ㅅ"),
  STANDARD_SCHNAUZER("스탠더드 슈나우저", "Standard schnauzer","ㅅ"),
  SPANISH_GREYHOUND("스패니쉬 그레이 하운드", "Spanish greyhound","ㅅ"),
  SPANISH_MASTIFF("스패니쉬 마스티프", "Spanish mastiff","ㅅ"),
  SPINONE_ITALIANO("스피노네 이탈리아노", "Spinone italiano","ㅅ"),
  SPITZ("스피츠", "Spitz","ㅅ"),
  SIGOREUJABEUJONG("시고르자브종", "Sigoreujabeujong","ㅅ"),
  SHIBA_INU("시바 이누", "Shiba inu","ㅅ"),
  SIBERIAN_HUSKY("시베리언 허스키", "Siberian husky","ㅅ"),
  CHICHU("시추", "Chichu","ㅅ"),
  SHIKOKU_KEN("시코쿠견", "Shikoku ken","ㅅ"),
  SEALYHAM_TERRIER("실리엄 테리어", "Sealyham terrier","ㅅ"),
  SILKY_TERRIER("실키 테리어", "Silky terrier","ㅅ"),

//  아나톨리아 셰퍼드	Anatolian Shepherd
//  아메리칸 불도그	American Bulldog
//  아메리칸 불리	American Bully
//  아메리칸 스태퍼드셔 테리어	American Staffordshire Terrier
//  아메리칸 아키다	American Akita
//  아메리칸 에스키모 도그	American Eskimo Dog
//  아메리칸 워터 스패니얼	American Water Spaniel
//  아메리칸 코커 스패니얼	American Cocker Spaniel
//  아메리칸 폭스하운드	American Foxhound
//  아이디	Aidi
//  아이리시 소프트코티드 휘튼 테리어	Soft-coated Wheaten Terrier
//  아이리시 레드 앤드 화이트 세터	Irish Red and White Setter
//  아이리시 세터	Irish setter
//  아이리시 울프 하운드	Irish Wolfhound
//  아이리시 워터 스패니얼	Irish Water Spaniel
//  아이리시 테리어	Irish Terrier
//  아키타	Akita Inu
//  아펜핀셔	Affenpinscher
//  아프간 하운드	Afghan Hound
//  알래스칸 맬러뮤트	Alaskan Malamute
//  알래스칸 클리카이	Alaskan Klee Kai
//  에스트렐라 마운틴 독	Estrela Mountain Dog
//  에어데일 테리어	Airedale Terrier
//  오브차카	Ovcharka
//  오스트레일리안 실키 테리어	Australian Silky Terrier
//  오스트레일리안 켈피	Australian Kelpie
//  오스트레일리언 셰퍼드	Australian Shepherd
//  오스트레일리언 캐틀 도그	Australian Cattle Dog
//  오스트레일리언 테리어	Australian Terrier
//  오터 하운드	Otterhound
//  올드 잉글리시 쉽독	Old English Sheepdog
//  와이머라너	Weimaraner
//  와이어 폭스 테리어	Wire Fox Terrier
//  와이어헤어드 포인팅 그리펀	Wirehaired Pointing Griffon
//  야쿠탄 라이카	Yakutian Laika
//  요크셔 테리어	Yorkshire Terrier
//  웨스트 하이랜드 화이트테리어	West Highland White Terrier
//  웰시 스프링어 스패니얼	Welsh Springer Spaniel
//  웰시 코기	Welsh Corgi
//  웰시 테리어	Welsh Terrier
//  이비전 하운드	Ibizan Hound
//  이탤리언 그레이하운드	Italian Greyhound
//  잉글리시 세터	English Setter
//  잉글리시 스프링어 스패니얼	English Springer Spaniel
//  잉글리시 코커 스패니얼	English Cocker Spaniel
//  잉글리시 토이 스패니얼	English Toy Spaniel
//  잉글리시 폭스하운드	English Foxhound
//
//  ㅈ
//  자이언트 슈나우저	Giant Schnauzer
//  재패니즈 친	Japanese Chin
//  재패니즈 스피츠	Japanese Spitz
//  잭 러셀 테리어	Jack Russell Terrier
//  저먼 셰퍼드	German Shepherd
//  저먼 쇼트헤어드 포인터	German Shorthaired Pointer
//  저먼 와이어헤어드 포인터	German Wirehaired Pointer
//  저먼 핀셔	German Pinscher
//  저먼 헌팅 테리어	German Hunting Terrier
//  제주개	Jeju dog
//  진돗개	Korean Jindo
//
//      ㅊ
//  차우차우	Chow Chow
//  차이니즈 샤페이	Chinese Shares-pei
//  차이니즈 크레스티드	Chinese Crested Dog
//  체서피크 베이 레트리버	Chesapeake Bay Retriever
//  체코슬로바키아 늑대개	Czechoslovakian Wolfdog
//  치와와	Chihuahua
//
//  ㅋ
//  카네 코르소	Cane corso
//  카디건 웰시 코기	Cardigan Welsh Corgi
//  카발리에 킹 찰스 스파니엘	Cavalier King Charles Spaniel
//  캉갈	Kangal Shepherd Dog
//  컬리코티드 레트리버	Curly-coated retriever
//  케리 블루 테리어	Kerry Blue Terrier
//  케언 테리어	Cairn Terrier
//  케이넌 도그	Canaan Dog
//  케이스혼트	Keeshond
//  코리안 마스티프	Korean Mastiff
//  코몬도르	Komondor
//  코커 스패니얼	Cocker Spaniel
//  콜리	Collie
//  쿠바스	Kuvasz
//  쿠이커혼제	Kooikerhondje
//  클럼버 스패니얼	Clumber Spaniel
//
//  ㅌ
//  토이 폭스 테리어	Toy Fox Terrier
//  티베탄 마스티프	Tibetan Mastiff
//  티베탄 스패니얼	Tibetan spaniel
//  티베탄 테리어	Tibetan Terrier
//
//  ㅍ
//  파라오 하운드	Pharaoh Hound
//  파슨 러셀 테리어	Parson Russell Terrier
//  파피용	Papillon
//  패터데일 테리어	Patterdale Terrier
//  퍼그	Pug
//  페키니즈	Pekingese
//  펨브록 웰시 코기	Pembroke Welsh Corgi
//  포르투기즈 워터 도그	Portuguese Water Dog
//  포메라니안	Pomeranian
//  포인터	Pointer
//  폭스 테리어	Fox Terrier
//  폴리시 롤런드 시프도그	Polish Lowland Sheepdog
//  폼피츠	pompitz
//  푸들	Puddle
//  푸미	Pumi
//  풀리	Puli dog
//  풍산개	Poongsan
//  프렌치 불도그	French Bulldog
//  프티 바세 그리퐁 방댕	Petit Basset Griffon Vendéen
//  플랫코티드 레트리버	Flat-coated Retriever
//  플롯 하운드	Plott Hound
//  피니시 스피츠	Finnish Spitz
//  피레니안 마스티프	Pyrenean Mastiff
//  피레니안 쉽독	Pyrenean Sheepdog
//  피레니언 셰퍼드	Pyrenean Shepherd Dog
//  필드 스패니얼	Field Spaniel
//  필라 브라질레이로	Fila Brasileiro
//  핏 불 테리어	Pit bull
//
//      ㅎ
//  해리어	Harrier
//  하바니즈	Havanese
//  홋카이도 이누	Hokkaido Dog
//  휘핏	Whippet

  ;


  private final String korName;
  private final String engName;
  private final String firstName;

}
