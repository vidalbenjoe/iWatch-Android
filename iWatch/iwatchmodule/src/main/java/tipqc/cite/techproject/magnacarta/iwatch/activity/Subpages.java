package tipqc.cite.techproject.magnacarta.iwatch.activity;

import java.util.LinkedList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

import tipqc.cite.techproject.magnacarta.R;


public class Subpages extends Activity implements View.OnClickListener, AnimationListener{

	TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
	TextView tv11, tv12, tv13, tv14, tv15, tv16, tv17, tv18, tv19, tv20;
	TextView tv21, tv22;
	int btnClicked = 0;
	Animation slideDown;
	SharedPreferences sp;
	public static String file = "Files";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//fullscreen
  		requestWindowFeature (Window.FEATURE_NO_TITLE);
  		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
  		WindowManager.LayoutParams.FLAG_FULLSCREEN);
  		setContentView(R.layout.subpages);
  		
  		sp = getSharedPreferences(file,0);
  		
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv12 = (TextView) findViewById(R.id.tv12);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv15 = (TextView) findViewById(R.id.tv15);
        tv16 = (TextView) findViewById(R.id.tv16);
        tv17 = (TextView) findViewById(R.id.tv17);
        tv18 = (TextView) findViewById(R.id.tv18);
        tv19 = (TextView) findViewById(R.id.tv19);
        tv20 = (TextView) findViewById(R.id.tv20);
        tv21 = (TextView) findViewById(R.id.tv21);
        tv22 = (TextView) findViewById(R.id.tv22);
        
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.slide_down);
     
        tv1.setOnClickListener(this);        
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tv10.setOnClickListener(this);
        tv11.setOnClickListener(this);
        tv12.setOnClickListener(this);
        tv13.setOnClickListener(this);
        tv14.setOnClickListener(this);
        tv15.setOnClickListener(this);
        tv16.setOnClickListener(this);
        tv17.setOnClickListener(this);
        tv18.setOnClickListener(this);
        tv19.setOnClickListener(this);
        tv20.setOnClickListener(this);
        tv21.setOnClickListener(this);
        tv22.setOnClickListener(this);
        
        slideDown.setAnimationListener(this);
        tv1.startAnimation(slideDown);
        btnClicked = sp.getInt("storedbtnclicked", 0);
        btnClickCheck();
        
	}
	@Override
	public void onClick(View v){
		// TODO Auto-generated method stub
	}
	public void btnClickCheck(){
		switch(btnClicked){
		case 1:
			//tv1.setText("");
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.GONE);
			tv4.setVisibility(View.GONE);
			tv5.setVisibility(View.GONE);
			tv6.setVisibility(View.GONE);
			tv7.setVisibility(View.GONE);
			tv8.setVisibility(View.GONE);
			tv9.setVisibility(View.GONE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nWhat is the Magna Carta of Women?\n");
			tv2.setText("\nThe RA 9710 or the magna car-" +
                    "ta of women is a comprehensive women's" +
                    " human rights law that seeks to eliminate " +
                    "discrimination against women by recognizing," +
                    " protecting, fulfilling and promoting the " +
                    "rights of Filipino women, especially those in" +
                    "the marginalized sectors.\n");
			break;
		case 2:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.GONE);
			tv6.setVisibility(View.GONE);
			tv7.setVisibility(View.GONE);
			tv8.setVisibility(View.GONE);
			tv9.setVisibility(View.GONE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nWhat are the penalties of violators?\n");
			tv2.setText("\nIf the violation is committed by a government agency or any government office, including government-owned and controlled corporations and local government units, the person directly  responsible for the violation, as well as the head of the agency or local chief executive shall be held liable under the Magna Carta of Women. The sanctions under administrative law, civil service or other appropriate laws shall be recommended by the Commission on Human Rights to the Civil Service Commission and the Department of the Interior and Local Government. Further, in cases where violence has been proven to be perpetrated by agents of the State, such shall be considered on the severity of the offenses.\n");
			tv3.setText("\nIf the violation is committed by a private entity or individual, the person directly responsible for the violation shall be liable to pay damages.\n");
			tv4.setText("\nFurther, the offended party can also pursue other remedies available under the law and can invoke any of the other provisions of existing laws, especially those that  protect the rights of women.\n");
			break;
		case 3:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.VISIBLE);
			tv7.setVisibility(View.VISIBLE);
			tv8.setVisibility(View.VISIBLE);
			tv9.setVisibility(View.GONE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nWhat you should know about violence against women (VAW)?\n");
			tv2.setText("\nVAW is a threat to human security as it threatens the daily lives of half of humanity.\n");
			tv3.setText("\nVAW cuts across race, religion, age, class, and sexuality. It is rooted in unequal gender relations where men have the power and control over women.\n");
			tv4.setText("\nVAW do not only happen to poor people. It also happens to women in middle and upper class of society.\n");
			tv5.setText("\nDomestic violence and intimate partner abuse destroy love, respect and honor-the very foundation on which people build their homes and relationship.\n");
			tv6.setText("\nDomestic violence and intimate partner abuse are NOT private matters. These are public crimes that violate a person's right to life, personal dignity and security.\n");
			tv7.setText("\nEmotional, structural, societal, and cultural factors keep women in violent relationships love and concern for children, lack of resources to escape, lack of education and awareness, and the notion that family preservation should be maintained at all costs.\n");
			tv8.setText("\nVictim-survivors should not be BLAMED for being in violent relationships. Rather, support and assistance should be extended to them to help them get out of the situation and survive the ordeal.\n");
			break;
		case 4:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.VISIBLE);
			tv7.setVisibility(View.VISIBLE);
			tv8.setVisibility(View.VISIBLE);
			tv9.setVisibility(View.VISIBLE);
			tv10.setVisibility(View.VISIBLE);
			tv11.setVisibility(View.VISIBLE);
			tv12.setVisibility(View.VISIBLE);
			tv13.setVisibility(View.VISIBLE);
			tv14.setVisibility(View.VISIBLE);
			tv15.setVisibility(View.VISIBLE);
			tv16.setVisibility(View.VISIBLE);
			tv17.setVisibility(View.VISIBLE);
			tv18.setVisibility(View.VISIBLE);
			tv19.setVisibility(View.VISIBLE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\n18 Things you can do to help end violence against women now\n");
			tv2.setText("\n1.\t\tTell people VAW is never okay!\n");
			tv3.setText("\n2.\t\tLearn about VAW and what causes it.\n");
			tv4.setText("\n3.\t\tEncourage and support those people in your community working to end all forms of VAW.\n");
			tv5.setText("\n4.\t\tRespect women and young girls.\n");
			tv6.setText("\n5.\t\tRaise your sons and daughters to be equal and teach them that there is nothing that boys can do that girls cannot.\n");
			tv7.setText("\n6.\t\tOrganize forums or sessions in your community, workplace or schools regarding violence against women.\n");
			tv8.setText("\n7.\t\tTeach boys and young men about how to be men in ways that they don't involve degrading or abusing girls and women.\n");
			tv9.setText("\n8.\t\tMake your home violence-free.\n");
			tv10.setText("\n9.\t\tParticipate in online discussions and social media campaign to create awareness in ending VAW.\n");
			tv11.setText("\n10.\tVolunteer for a cause to end VAW.\n");
			tv12.setText("\n11.\tDiscuss things with your partner and respect each other\'s opinion.\n");
			tv13.setText("\n12.\tTalk with your partners, friends and family on how you can support each other in stopping VAW.\n");
			tv14.setText("\n13.\tDon\'t make fun out of VAW or use it as material for comedy.\n");
			tv15.setText("\n14.\tEducate young girls and boys about VAW.\n");
			tv16.setText("\n15.\tDon\'t use disrespectful words.\n");
			tv17.setText("\n16.\tNever judge people who suffered from abuses and VAW.\n");
			tv18.setText("\n17.\tMen: never remain silent, never condone, and never commit acts of VAW!\n");
			tv19.setText("\n18.\tSpeak up! Never remain silent if you are victim of VAW.\n");
			break;
		case 5:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.VISIBLE);
			tv7.setVisibility(View.VISIBLE);
			tv8.setVisibility(View.GONE);
			tv9.setVisibility(View.GONE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nAnti-VAW laws\n");
			tv2.setText("\nThe following are laws that uphold and protect women's rights. For more details on these laws, you may visit the Philippine commission on Women website (www.pcw.gov.ph)\n");
			tv3.setText("\nRA 7877: Anti Sexual Harassment act of 1995\n");
			tv4.setText("\nRA 8353: Anti-Rape law of 1997\n");
			tv5.setText("\nRA 9208: The Anti-Trafficking in Persons Act of 2003\n");
			tv6.setText("\nRA 9262: The Anti-Violence Against Women and Their Children Act of 2004\n");
			tv7.setText("\nRA 9710: Magna Carta of Women (2009)\n");
			
			break;
		case 6:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.VISIBLE);
			tv7.setVisibility(View.VISIBLE);
			tv8.setVisibility(View.GONE);
			tv9.setVisibility(View.GONE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nStatistics on VAW\n");
			tv2.setText("\nOne in five women aged 15-49 experienced physical violence since age 15!\n");
			tv3.setText("\n8% of ever-married women experienced sexual violence perpetrated by their husbands!\n");
			tv4.setText("\nAlmost one in 10 women aged 15-49 experienced sexual violence!\n");
			tv5.setText("\nIt is also very alarming to know what women are not safe even in their own homes! 14.4% of ever-married women experienced physical violence perpetrated by their husbands!\n");
			tv6.setText("\nAlmost 4 out of 100 pregnant women experienced physical violence (3.6%)!\n");
			tv7.setText("\nBetween 2008-2009, cases of VAW reported to the Philippine national police (PNP) rose by 37.4% (from 6,905 in 2008 to 9,485 in 2009) and in 2010, VAW cases reported to PNP again rose to 59% (15,104 in 2010).\n");
			break;
		case 7:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.VISIBLE);
			tv7.setVisibility(View.VISIBLE);
			tv8.setVisibility(View.VISIBLE);
			tv9.setVisibility(View.VISIBLE);
			tv10.setVisibility(View.VISIBLE);
			tv11.setVisibility(View.VISIBLE);
			tv12.setVisibility(View.VISIBLE);
			tv13.setVisibility(View.VISIBLE);
			tv14.setVisibility(View.VISIBLE);
			tv15.setVisibility(View.VISIBLE);
			tv16.setVisibility(View.VISIBLE);
			tv17.setVisibility(View.VISIBLE);
			tv18.setVisibility(View.VISIBLE);
			tv19.setVisibility(View.VISIBLE);
			tv20.setVisibility(View.VISIBLE);
			tv21.setVisibility(View.VISIBLE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nWhen you are in abusive relationship\n");
			tv2.setText("\n\nPhysical or sexual violence may occur without warning. Sometimes, however, there may be signs or 'red flags' that serve as warnings that abuse may occur. The following are examples of a person's behavior or personality that may be a warning that a person may be abusive. If you answer YES to one or more of these questions, you may be in an abusive relationship or be at risk for it.\n");
			tv3.setText("\nDoes your husband, partner or boyfriend...\n");
			tv4.setText("\nTease you in a hurtful way in private or public?\n");
			tv5.setText("\nCall you names such as \"stupid\",\"bitch\",\"tanga\",\"bobo\",etc?\n");
			tv6.setText("\nAct jealous of your friends, family and co-workers or accuse you of being interested in someone else?\n");
			tv7.setText("\nGet angry about clothes you wear or how you style your hair?\n");
			tv8.setText("\nCheck on you by calling, driving by, or getting someone else to?\n");
			tv9.setText("\nGone places with you or sent someone just to \"keep an eye on you\"?\n");
			tv10.setText("\nAlways insist on knowing who you talk with on the phone?\n");
			tv11.setText("\nBlame you or his problems or his bad mood, or get angry so easily?\n");
			tv12.setText("\nHit walls, drive dangerously, or do other things to scare you?\n");
			tv13.setText("\nOften drink or use drugs or insist that you drink or use drugs with him?\n");
			tv14.setText("\nRead your mail, check your cellphone messages, go through your purse, or other personal papers?\n");
			tv15.setText("\nKeep money from you, keep you in debt, or have money secrets?\n");
			tv16.setText("\nThreaten to hurt you, your family, friends, or pets?\n");
			tv17.setText("\nForce you to have sex when you do not want to or force you to have sex in ways that you do not want to?\n");
			tv18.setText("\nThreaten to kill you or himself if you leave?\n");
			tv19.setText("\nAct one way in front of other people and another way when you are alone?\n");
			tv20.setText("\nCause the loss of your friends or no longer see some of your family because of your partner/cause you to stay out of touch with your family and friends?\n");
			tv21.setText("\n\nIf you are concerned about the possibility that your partner is showing early signs of abuse, SEEK HELP NOW! If you are in ABUSIVE RELATIONSHIP and is considering ending your relationship, it would be helpful to tell your parents or confide with a friend or school teacher or counselor in order to have support when leaving the relationship.\n");
			break;
		case 8:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.VISIBLE);
			tv7.setVisibility(View.VISIBLE);
			tv8.setVisibility(View.VISIBLE);
			tv9.setVisibility(View.VISIBLE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nIf you're planning to leave an abusive relationship:\n");
			tv2.setText("\nTell other people that you plan to break up with your partner. Let them know where you will be.\n");
			tv3.setText("\nArrange to call a friend or a counselor after you talk with your partner so that you can debrief about what happened.\n");
			tv4.setText("\nMake a list of important phone numbers. Included on this list should be emergency numbers, as well as supportive friends whom you can call when you are upset. Put the numbers of crisis lines on the list.\n");
			tv5.setText("\nConsider looking into resources at your school or in the community. Think about joining a support group or calling a crisis line.\n");
			tv6.setText("\nChange your routine. Don't always come to school or work the same way or arrive at the same time. If you take the bus or any public transport, try to have someone with you.\n");
			tv7.setText("\nIf you are alone at home, make sure the doors are locked and the windows are secure.\n");
			tv8.setText("\nTry to double date or to go out with a group of people.\n");
			tv9.setText("\nTrust your instincts. Of you feel you are in danger, call the police. Get help immediately.\n");
			break;
		case 9:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.VISIBLE);
			tv7.setVisibility(View.GONE);
			tv8.setVisibility(View.GONE);
			tv9.setVisibility(View.GONE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nSafety tips for young women\n");
			tv2.setText("\nStay in touch with your family and friends and make it a point to send time with people other than your partner. Let other people know what your plans are and where you will be.\n");
			tv3.setText("\nStay involved in activities that you enjoy. Don't stop doing things that you enjoy and make you feel good about yourself.\n");
			tv4.setText("\nMake new friends. Increase your support network.\n");
			tv5.setText("\nTry not to be dependent on your partner for a ride.\n");
			tv6.setText("\nConsider telling your parents or other family members if you are having problems.\n");
			break;
		case 10:
			tv1.setVisibility(View.VISIBLE);
			tv2.setVisibility(View.VISIBLE);
			tv3.setVisibility(View.VISIBLE);
			tv4.setVisibility(View.VISIBLE);
			tv5.setVisibility(View.VISIBLE);
			tv6.setVisibility(View.GONE);
			tv7.setVisibility(View.GONE);
			tv8.setVisibility(View.GONE);
			tv9.setVisibility(View.GONE);
			tv10.setVisibility(View.GONE);
			tv11.setVisibility(View.GONE);
			tv12.setVisibility(View.GONE);
			tv13.setVisibility(View.GONE);
			tv14.setVisibility(View.GONE);
			tv15.setVisibility(View.GONE);
			tv16.setVisibility(View.GONE);
			tv17.setVisibility(View.GONE);
			tv18.setVisibility(View.GONE);
			tv19.setVisibility(View.GONE);
			tv20.setVisibility(View.GONE);
			tv21.setVisibility(View.GONE);
			tv22.setVisibility(View.GONE);
			
			tv1.setText("\nWhat is the discrimination against women?\n");
			tv2.setText("\nAny gender-based distinction, exclusion, or restriction which has the effect or purpose of impairing or nullifying the recognition, enjoyment, or exercise by women, irrespective of their marital status, on a basis of equality of men and women, of human rights and fundamental freedoms in the political, economic, social, cultural, civil or any other field.\n");
			tv3.setText("\nAny act or omission, including by law, policy, administrative measure, or practice, that directly or indirectly excludes or restricts women in the recognition and promotion of their rights and their access to and enjoyment if opportunities, benefits, or privileges.\n");
			tv4.setText("\nA measure or practice of general application that fails to provide for mechanisms to offset or address sex or gender-based disadvantages or limitations of women, as a result of which women are denied or restricted in the recognition and protection of their rights and in their access to and enjoyment of opportunities, benefits, or privileges; or women, more than men are shown to have suffered the greater adverse effects of those measures or practices; and\n");
			tv5.setText("\nDiscrimination compounded by or intersecting with other grounds, status, or condition, such as ethnicity, age, poverty, or religion.\n");
			break;
		default:
			
	}
	}
	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}




}
