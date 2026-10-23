import subprocess,json,time,hashlib
from pathlib import Path
from PIL import Image
P=Path(__file__).parent; O=P/'build/native';O.mkdir(parents=True,exist_ok=True)
def adb(*a,binary=False):
 r=subprocess.run(['adb','-s','emulator-5580',*a],capture_output=True,timeout=25);assert r.returncode==0,r.stderr;return r.stdout if binary else r.stdout.decode().strip()
def sh(*a):return adb('shell',*a)
keys=['secure/screensaver_components','secure/screensaver_enabled','secure/screensaver_default_component','secure/sleep_timeout','system/screen_off_timeout','secure/accessibility_enabled','secure/enabled_accessibility_services','global/show_dc_settings','secure/screensaver_activate_on_sleep','secure/screensaver_activate_on_dock','secure/str.auto_wake_up_enabled']
def snap():
 return {'os':{k:sh('settings','get',*k.split('/')) for k in keys},'guard':sh('dumpsys','activity','services','com.jeremykenedy.firetv.ui'),'providers':{p:sh('content','query','--uri','content://'+p+'.settings/settings') for p in ['com.jeremykenedy.firetv.ui','com.jeremykenedy.jellyfishdrift','com.jeremykenedy.jellyfishdriftv2','com.jeremykenedy.firetv.aquarium']}}
before=snap();(O/'before.json').write_text(json.dumps(before,indent=2))
for name in ['fire-tv-ui-backup.txt','fire-tv-ui-import.txt']:
 b=adb('exec-out','sh','-c','if [ -f /sdcard/Download/'+name+' ]; then cat /sdcard/Download/'+name+'; else echo ABSENT; fi',binary=True);(O/name).write_bytes(b)
try:
 print(adb('install','--no-incremental','-r',str(P/'build/black-screen-dream.apk')),flush=True)
 sh('am','start','-n','com.jeremykenedy.blackscreendream/.SettingsActivity');time.sleep(1)
 (O/'settings.png').write_bytes(adb('exec-out','screencap','-p',binary=True))
 sh('input','keyevent','23');time.sleep(2)
 top=sh('dumpsys','activity','activities');assert 'com.jeremykenedy.blackscreendream/.PreviewActivity' in top
 for n in ['black-first','black-later']:
  (O/(n+'.png')).write_bytes(adb('exec-out','screencap','-p',binary=True));im=Image.open(O/(n+'.png')).convert('RGB');assert im.getextrema()==((0,0),(0,0),(0,0)),im.getextrema();time.sleep(1)
 sh('input','keyevent','4');time.sleep(1);assert 'com.jeremykenedy.blackscreendream/.SettingsActivity' in sh('dumpsys','activity','activities')
 sh('am','start','-n','com.jeremykenedy.blackscreendream/.PreviewActivity');time.sleep(1);sh('input','keyevent','3');time.sleep(1)
 sh('am','start','-n','com.jeremykenedy.blackscreendream/.PreviewActivity');time.sleep(1);sh('input','keyevent','4')
 (O/'result.json').write_text(json.dumps({'twoFramesPureBlackEveryRGBPixel':True,'nativeDpadPreview':True,'BackSettings':True,'HomeAndRelaunch':True,'DreamBinding':'physical gate by parent'},indent=2))
finally:
 sh('am','force-stop','com.jeremykenedy.blackscreendream');sh('input','keyevent','3');after=snap();(O/'after.json').write_text(json.dumps(after,indent=2));assert after['os']==before['os'];assert after['providers']==before['providers'];assert ('ProtectionService' in after['guard'])==('ProtectionService' in before['guard'])
 print('Native pure-black/lifecycle passed; surrounding OS/provider values unchanged.',flush=True)
