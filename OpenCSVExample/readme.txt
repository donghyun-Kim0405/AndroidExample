저장소 접근 방법 



안드로이드는 저장소를 내부와 외부 저장소로 나누어 관리하고 있다. 
안드로이드 버전 10을 기준으로 저장소의 접근 방식이 달라 졌다. 
버전 10 이전의 경우 Legacy Storage라는 형태로 관리하였으며 
버전 10 이후의 경우 Scoped Storage 라는 형태로 관리 되고 있다. 

이유는 다음과 같다. 
1.  해당 앱이 사용자에 의해 삭제 되어도 앱이 남긴 파일은 삭제되지 않고 외부 저장소에 남아 있게 된다. 
2. 외부의 다른 앱이 해당 앱에 필요한 파일을 읽거나 쓸 우려가 있다. 만일 해당 파일이 앱의 중요한 파일이 될경우 앱 의 정상 동작에 치명적일 수 있다.

이러한 이유로 구글에서는 샌드박스 형식의 내부 저장소를 사용할것을 권장하고 있다. 
그리고 기존의 외부 저장소 접근 방식은 deprecate될 예정이다. 
ex) requestLegacy  - Manifest
Environment.getExternalStorageDirectory() - Activity


그리고 안드로이드 11 버전 부터는 
미디어 파일의 경우 권한 없이 접근이 가능하고 
권한 없이 접근할 수 있는 디렉터리의 경우 다음의 절차를 따른다. 
Context.getExternalFilesDir(String type)

위 파라미터로 String type에는 다음제시되는 것들이 사용될 수 있다. 

* Environment.DIRECTORY_MUSIC 음악 파일 저장
* Environment.DIRECTORY_PODCASTS 팟캐스트 파일 저장
* Environment.DIRECTORY_DOWNLOADS 다운로드한 파일 저장
* Environment.DIRECTORY_ALARMS 알람으로 사용할 오디오 저장
* Environment.DIRECTORY_NOTIFICATIONS 알림음 오디오 저장
* Environment.DIRECTORY_PICTURES 그림 파일 저장
* Environment.DIRECTORY_MOVIES 영상 파일 저장
* Environment.DIRECTORY_DCIM 사진 파일 저장 


그 외의 파일을 쓰거나 읽고자 할 경우에는 내부 저장소를 사용해야 한다. 
내부 저장소 접근 방식은 다음과 같다. 


val appSpecificExternalDir = File(context.getExternalFilesDir(), filename)
//Cache file
val externalCacheFile = File(context.externalCacheDir, filename)


저장된 파일 경로 /data/user/0/com.example.opencsvexample/files 
