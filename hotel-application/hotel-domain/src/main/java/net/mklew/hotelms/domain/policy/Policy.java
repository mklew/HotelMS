package net.mklew.hotelms.domain.policy;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:47 PM
 */

/**
 * A policy is typically described as a principle or rule to guide
 decisions and achieve rational outcomes.
 Hotel may have many different policies. More relaxed policies
 could be assigned to specific guest as kind of benefit.
 Example of policy is:

 Charge guest with 100% of deposit on no show
 or
 Charge guest with 20% of deposit on cancellation

 these are No show policy and Cancellation policy.

 Other policies include:
 - Early check-in policy
 - Late check-in policy
 - No-show policy
 - Modification policy
 - Cancellation policy
 - Late check-out policy
 - Early check-out policy
 - Stay extension policy
 - Deposit return policy ??

 In Hotel business there are many business rules that are somewhat
 related to such situations as Early check-in and alike so having
 right place for all of these policies allows easy creation and
 modification of business rules.

 Real power of having policies defined is ability to assign
 different policies to different guests or even reservations. That
 gives us flexibility. For example hotel can have default set of
 policies and can offer some package which have other policies.
 Let's say that cancellations are charged 20% on regular basis but
 for specific weekend package cancellations would be restricted
 that is would be charged 100% of package value. That would be very
 hard if not impossible to achieve if that concept would be
 implicit and hardcoded somewhere.
 */
public class Policy
{

}
